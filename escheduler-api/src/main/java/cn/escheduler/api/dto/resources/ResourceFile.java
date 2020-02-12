package cn.escheduler.api.dto.resources;

import cn.escheduler.common.enums.ResourceType;
import cn.escheduler.dao.model.Resource;

/**
 * Created by Administrator on 2020/2/12.
 */
public class ResourceFile extends ResourceComponent{

    public ResourceFile(Resource resource) {
        super(resource);
        resource.setType(ResourceType.FILE);
    }
}
