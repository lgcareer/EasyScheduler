package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2020/2/13.
 */
public class ResourceVisitor implements Visitor{
    String suffix;
    ArrayList files=new ArrayList();

    public ResourceVisitor(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void visit(ResourceItem resourceItem) {
        if(resourceItem.getAlias().endsWith(suffix)) {
            files.add(resourceItem);
        }
    }

    @Override
    public void visit(ResourceDirectory resourceDirectory) {
        Iterator it=resourceDirectory.getChildren().iterator();
        while(it.hasNext()){
            ResourceComponent resourceComponent=(ResourceComponent) it.next();
            resourceComponent.accept(this);
        }
    }

    @Override
    public void visit(List<Resource> resource) {

    }
}
