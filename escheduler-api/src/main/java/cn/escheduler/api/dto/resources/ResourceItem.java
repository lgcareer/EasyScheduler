package cn.escheduler.api.dto.resources;

import cn.escheduler.common.enums.ResourceType;

/**
 * Created by Administrator on 2020/2/12.
 */
public class ResourceItem extends ResourceComponent {

    private cn.escheduler.dao.model.Resource resource;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ResourceItem(cn.escheduler.dao.model.Resource resource) {
        this.resource = resource;
    }

    public ResourceItem(int id, int pid, String alias, ResourceType type, int permission) {
        this.id = id;
        this.pid = pid;
        this.alias = alias;
        this.type = type;
        this.permission = permission;
    }

    public ResourceItem(int id, int pid, String alias, String fullName,ResourceType type, int permission) {
        this.id = id;
        this.pid = pid;
        this.alias = alias;
        this.fullName = fullName;
        this.type = type;
        this.permission = permission;
    }




    /**
     * resource aliases are used for uploading, downloading, and so on
     */
    private String alias;
    /**
     * resource type
     */
    private ResourceType type;
    /**
     * 1 has permission;0 no permission
     */
    private int permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "ResourceItem{" +
                "id=" + id +
                ", pid=" + pid +
                ", alias='" + alias + '\'' +
                ", type=" + type +
                ", permission=" + permission +
                '}';
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }
}
