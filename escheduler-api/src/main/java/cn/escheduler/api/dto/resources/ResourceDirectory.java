package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2020/2/12.
 */
public class ResourceDirectory extends ResourceComponent {



    public ResourceDirectory(String name, String fullName,String description) {
        this.name = name;
        this.description = description;
    }

    public ResourceDirectory(String fullName, String description) {
        this.fullName = fullName;
        this.description = description;
    }


    @Override
    public void add(ResourceComponent resourceComponent) {
        children.add(resourceComponent);
    }

    public void printJson(){
        System.out.print("\n" + super.getName());
        System.out.println(", "+super.getDescription());
        System.out.println("---------------------------");

        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            ResourceComponent resourceComponent = (ResourceComponent) iterator.next();
            resourceComponent.print();
        }
    }

    public void print(){
        List<Resource> resourceList;
        List<ResourceComponent> resourceComponentList = new ArrayList<>();
        resourceComponentList.add(this);
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            ResourceComponent resourceComponent = (ResourceComponent) iterator.next();
            //resourceList.add()
            resourceComponentList.add(resourceComponent);
            //resourceComponent.print();
        }
        System.out.println(JSON.toJSONString(resourceComponentList, SerializerFeature.SortField));
    }

    public void accept(Visitor visitor) {
        //  System.out.println("开始访问文件夹:"+this);
        visitor.visit(this);
        //   System.out.println("结束访问文件夹:"+this);
        //   System.out.println();
    }

    @Override
    public List<Resource> treeBuilder() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resource =new Resource();
        BeanUtils.copyProperties(this,resource);
        resourceList.add(resource);
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            ResourceComponent resourceComponent = (ResourceComponent) iterator.next();
            //resourceList.add()
            Resource tmpResource =new Resource();
            BeanUtils.copyProperties(this,tmpResource);
            resourceList.add(tmpResource);
            //resourceComponent.print();
        }
        return resourceList;
    }
}
