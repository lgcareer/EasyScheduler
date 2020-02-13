package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2020/2/13.
 */
public class ListVisitor implements Visitor{

    @Override
    public void visit(ResourceItem resourceItem) {
        System.out.println(resourceItem.getFullName());
    }

    @Override
    public void visit(ResourceDirectory resourceDirectory) {
        System.out.println(resourceDirectory.getFullName());
        Iterator it=resourceDirectory.getChildren().iterator();
        while(it.hasNext()){
            ResourceComponent resourceComponent=(ResourceComponent) it.next();
            resourceComponent.accept(this);
        }
    }

    @Override
    public void visit(List<Resource> resourceList) {

    }


}
