package cn.escheduler.api.dto.resources;

import cn.escheduler.api.service.ResourcesService;
import cn.escheduler.dao.model.Resource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2020/2/13.
 */
public class ResorceTreeVisitor implements Visitor{
    private static final Logger logger = LoggerFactory.getLogger(ResorceTreeVisitor.class);
    private List<Resource> nodes;
    @Override
    public void visit(ResourceItem resourceItem) {

    }

    @Override
    public void visit(ResourceDirectory resourceDirectory) {

    }

    @Override
    public void visit(List<Resource> resourceList) {

    }

    /**
     * Construct a JSON tree structure
     *
     * @return
     */

    /*public String buildJSONTree() {
        //Collections.sort(this.nodes);
        List<ResourceViewDto> nodeTree = buildTree();
        logger.info(nodeTree.toString());

        return JSON.toJSONString(nodeTree, SerializerFeature.SortField);

    }*/

    /**
     * Build a tree structure
     *
     * @return
     *//*

    public List<ResourceViewDto> buildTree() {

        List<ResourceViewDto> treeNodes = new ArrayList<ResourceViewDto>();
        List<ResourceViewDto> rootNodes = getRootNodes();

        for (ResourceViewDto rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }

        return treeNodes;

    }*/


}
