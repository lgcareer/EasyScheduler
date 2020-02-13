package cn.escheduler.api.dto.resources;

import cn.escheduler.dao.model.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/12.
 */
public abstract class ResourceComponent {
    public ResourceComponent() {
    }
    public abstract void accept(Visitor visitor);
    public ResourceComponent(Resource resource) {
        this.resource = resource;
        this.id = resource.getId();
        this.pid = resource.getPid();
        this.name = resource.getAlias();
        this.description = resource.getDesc();
        this.fullName = resource.getFullName();
        this.isDirctory = resource.isDirectory();
    }

    public ResourceComponent(int id, int pid, String name, String fullName, String description, boolean isDirctory) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.isDirctory = isDirctory;
    }

    protected Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * id
     */
    protected int id;
    /**
     * parent id
     */
    protected int pid;
    protected String name;
    protected String fullName;
    protected String description;
    protected boolean isDirctory;
    /**
     * children
     */
    protected List<ResourceComponent> children = new ArrayList<>();
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
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDirctory() {
        return isDirctory;
    }

    public void setDirctory(boolean dirctory) {
        isDirctory = dirctory;
    }

    public List<ResourceComponent> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceComponent> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ResourceComponent{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", isDirctory=" + isDirctory +
                ", children=" + children +
                '}';
    }

    public void print(){
        System.out.println(toString());
    }

    public List<Resource> treeBuilder(){
        return null;
    }

}
