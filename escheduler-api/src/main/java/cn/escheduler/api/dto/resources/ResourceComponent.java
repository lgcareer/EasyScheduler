package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;

/**
 * Created by Administrator on 2020/2/12.
 */
public abstract class ResourceComponent {
    public ResourceComponent(Resource resource){}

    public void add(ResourceComponent resourceComponent){
        throw new UnsupportedOperationException();
    }

    public void remove(ResourceComponent resourceComponent){
        throw new UnsupportedOperationException();
    }

    public void getChild(int i){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public String getDescription(){
        throw new UnsupportedOperationException();
    }

    public void print(){
        throw new UnsupportedOperationException();
    }
}
