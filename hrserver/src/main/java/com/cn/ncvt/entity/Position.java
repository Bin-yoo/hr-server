package com.cn.ncvt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @version : V1.0
 * @ClassName: Position
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:26
 **/
public class Position {

    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
    private Timestamp createDate = new Timestamp(((new java.util.Date()).getTime()));
    private String remark;

    public Position(String name) {
        this.name = name;
    }

    public Position(Integer id, String name, Timestamp createDate, String remark) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
