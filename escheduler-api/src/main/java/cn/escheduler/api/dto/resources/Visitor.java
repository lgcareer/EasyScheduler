package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;

import java.util.List;

/**
 * Created by Administrator on 2020/2/13.
 */
public interface Visitor {
    public void visit(ResourceItem resourceItem);
    public void visit(ResourceDirectory resourceDirectory);
    public void visit(List<Resource> resourceList);
}
