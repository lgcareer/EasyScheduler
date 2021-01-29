/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.server.master.registry;

import org.apache.dolphinscheduler.common.enums.ZKNodeType;
import org.apache.dolphinscheduler.common.utils.StringUtils;
import org.apache.dolphinscheduler.remote.utils.Constants;
import org.apache.dolphinscheduler.server.master.MasterServer;
import org.apache.dolphinscheduler.server.master.config.MasterConfig;
import org.apache.dolphinscheduler.server.registry.ZookeeperRegistryCenter;
import org.apache.dolphinscheduler.server.zk.SpringZKServer;
import org.apache.dolphinscheduler.server.zk.ZKMasterClient;
import org.apache.dolphinscheduler.service.process.ProcessService;
import org.apache.dolphinscheduler.service.zk.AbstractZKClient;
import org.apache.dolphinscheduler.service.zk.ZookeeperCachedOperator;
import org.apache.dolphinscheduler.service.zk.ZookeeperConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.dolphinscheduler.common.Constants.*;
import static org.apache.dolphinscheduler.common.Constants.SINGLE_SLASH;
import static org.apache.dolphinscheduler.common.Constants.UNDERLINE;

/**
 * master registry test
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={SpringZKServer.class, MasterRegistry.class,ZookeeperRegistryCenter.class, MasterConfig.class, ZookeeperCachedOperator.class, ZookeeperConfig.class})
public class MasterRegistryTest {

    @Autowired
    private MasterRegistry masterRegistry;

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private MasterConfig masterConfig;

    /**
     * logger of MasterServer
     */
    private static final Logger logger = LoggerFactory.getLogger(MasterRegistryTest.class);


    @Test
    public void testRegistry() throws InterruptedException {
        // register
        String registPath = masterRegistry.getMasterPath();


        try {
            handleDeadServer(registPath, ZKNodeType.MASTER, org.apache.dolphinscheduler.common.Constants.DELETE_ZK_OP);
            masterRegistry.registry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String masterPath = zookeeperRegistryCenter.getMasterPath();
        TimeUnit.SECONDS.sleep(masterConfig.getMasterHeartbeatInterval() * 10 + 2); //wait heartbeat info write into zk node
        String masterNodePath = masterPath + "/" + (Constants.LOCAL_ADDRESS + ":" + masterConfig.getListenPort());
        String heartbeat = zookeeperRegistryCenter.getRegisterOperator().get(masterNodePath);
        Assert.assertEquals(HEARTBEAT_FOR_ZOOKEEPER_INFO_LENGTH, heartbeat.split(",").length);
    }

    @Test
    public void testUnRegistry() throws InterruptedException {
        masterRegistry.registry();
        TimeUnit.SECONDS.sleep(masterConfig.getMasterHeartbeatInterval() + 2); //wait heartbeat info write into zk node
        masterRegistry.unRegistry();
        String masterPath = zookeeperRegistryCenter.getMasterPath();
        List<String> childrenKeys = zookeeperRegistryCenter.getRegisterOperator().getChildrenKeys(masterPath);
        Assert.assertTrue(childrenKeys.isEmpty());
    }


    @Test
    public void initSystemNodeTest(){
        masterRegistry.initSystemNode();
    }

    /**
     *  remove dead server by host
     * @param host host
     * @param serverType serverType
     * @throws Exception
     */
    public void removeDeadServerByHost(String host, String serverType) throws Exception {
        String deadZnodeParentPath = zookeeperRegistryCenter.getDeadZNodeParentPath();

        List<String> deadServers = zookeeperRegistryCenter.getRegisterOperator().getChildrenKeys(deadZnodeParentPath);
        for(String serverPath : deadServers){
            if(serverPath.startsWith(serverType+UNDERLINE+host)){
                String server = deadZnodeParentPath + SINGLE_SLASH + serverPath;
                zookeeperRegistryCenter.getRegisterOperator().remove(server);
                logger.info("{} server {} deleted from zk dead server path success" , serverType , host);
            }
        }
    }


    /**
     * opType(add): if find dead server , then add to zk deadServerPath
     * opType(delete): delete path from zk
     *
     * @param zNode   		  node path
     * @param zkNodeType	  master or worker
     * @param opType		  delete or add
     * @throws Exception errors
     */
    public void handleDeadServer(String zNode, ZKNodeType zkNodeType, String opType) throws Exception {
        String host = getHostByEventDataPath(zNode);
        String type = (zkNodeType == ZKNodeType.MASTER) ? MASTER_PREFIX : WORKER_PREFIX;

        //check server restart, if restart , dead server path in zk should be delete
        if(opType.equals(DELETE_ZK_OP)){
            removeDeadServerByHost(host, type);

        }else if(opType.equals(ADD_ZK_OP)){
            String deadServerPath = zookeeperRegistryCenter.getDeadZNodeParentPath() + SINGLE_SLASH + type + UNDERLINE + host;
            if(!zookeeperRegistryCenter.getRegisterOperator().isExisted(deadServerPath)){
                //add dead server info to zk dead server path : /dead-servers/

                zookeeperRegistryCenter.getRegisterOperator().persist(deadServerPath,(type + UNDERLINE + host));

                logger.info("{} server dead , and {} added to zk dead server path success" ,
                        zkNodeType.toString(), zNode);
            }
        }

    }

    /**
     *  get host ip, string format: masterParentPath/ip
     * @param path path
     * @return host ip, string format: masterParentPath/ip
     */
    protected String getHostByEventDataPath(String path) {
        if(StringUtils.isEmpty(path)){
            logger.error("empty path!");
            return "";
        }
        String[] pathArray = path.split(SINGLE_SLASH);
        if(pathArray.length < 1){
            logger.error("parse ip error: {}", path);
            return "";
        }
        return pathArray[pathArray.length - 1];

    }
}
