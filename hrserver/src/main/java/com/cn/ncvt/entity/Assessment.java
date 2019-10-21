package com.cn.ncvt.entity;

import java.sql.Timestamp;

/**
 * @version : V1.0
 * @ClassName: Assessment
 * @Description: 考核项目类
 * @Auther: Bin
 * @Date: 2019/10/21 15:41
 **/
public class Assessment {

    private int id;
    private String name;
    private String remarks;
    private String state;
    private Timestamp beginDate;
    private Timestamp endDate;
    private Timestamp createDate = new Timestamp(((new java.util.Date()).getTime()));

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
