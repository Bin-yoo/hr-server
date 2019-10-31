package com.cn.ncvt.entity;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: Department
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 15:50
 **/
public class Department {

    private int id;
    private String name;
    private int parentId;
    private String depPath;
    private List<Department> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDepPath() {
        return depPath;
    }

    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }
}
