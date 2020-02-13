package cn.escheduler.api.dto.resources;

import cn.escheduler.common.enums.ResourceType;
import cn.escheduler.dao.model.Resource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2020/2/12.
 */
public class ResourceComponentTest {
    public static void main(String[] args) {
        ResourceComponent resourceFileDirectory = new ResourceDirectory("/a1/b1","aaa");


        ResourceComponent allResourceComponent = new ResourceDirectory("/a1","all");
        //System.out.println(allResourceComponent.toString());

        //System.out.println(allResourceComponent.toString());
        resourceFileDirectory.add(new ResourceItem(1,-1,"bbb.txt", "/a1/b1/bbb.txt",ResourceType.FILE,1));
        //System.out.println(allResourceComponent.toString());

        allResourceComponent.add(resourceFileDirectory);
        allResourceComponent.accept(new ListVisitor());
        //allResourceComponent.print();

        //System.out.println(JSON.toJSONString(allResourceComponent, SerializerFeature.SortField));
        //allResourceComponent.print();

        /*List<ResourceComponent> resourceComponentList = new ArrayList<>();
        resourceComponentList.add(allResourceComponent);
        Iterator iterator = allResourceComponent.getChildren().iterator();
        while (iterator.hasNext()) {
            ResourceComponent resourceComponent = (ResourceComponent) iterator.next();
            //resourceList.add()
            resourceComponentList.add(resourceComponent);
            //resourceComponent.print();
        }
        System.out.println(JSON.toJSONString(resourceComponentList, SerializerFeature.SortField));*/

        /*List<Resource> resources = allResourceComponent.treeBuilder();
        System.out.println(JSON.toJSONString(resources, SerializerFeature.SortField));*/
    }



}
