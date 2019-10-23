package com.cn.ncvt.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by sang on 2017/12/28.
 */
public class Role implements Serializable {
    private int id;
    private String name;
    private Timestamp createDate = new Timestamp(((new java.util.Date()).getTime()));
    private String remark;

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
