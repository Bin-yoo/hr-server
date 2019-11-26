package com.cn.ncvt.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: Department
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 15:50
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)

public class Department {

    private Integer id;
    private Integer depNum;
    private Integer orderNum;
    private String name;
    private Integer parentId;
    private String depPath;
    private List<Department> children;

    private String title;
    private String label;
    private boolean expand = true;

    public Department(String name) {
        this.name = name;
    }

    public Department(Integer id, Integer depNum, Integer orderNum, String name, Integer parentId, String depPath) {
        this.id = id;
        this.depNum = depNum;
        this.orderNum = orderNum;
        this.name = name;
        this.parentId = parentId;
        this.depPath = depPath;
        this.children = children;
        this.title = title;
        this.label = label;
        this.expand = expand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepNum() {
        return depNum;
    }

    public void setDepNum(Integer depNum) {
        this.depNum = depNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }
}
