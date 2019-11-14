package com.cn.ncvt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EmployeeTransfer {
    private Integer id;

    private Integer eid;

    private String befDep;

    private String afterDep;

    private String befJobLvl;

    private String afterJobLvl;

    private String befPos;

    private String afterPos;

    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getBefDep() {
        return befDep;
    }

    public void setBefDep(String befDep) {
        this.befDep = befDep;
    }

    public String getAfterDep() {
        return afterDep;
    }

    public void setAfterDep(String afterDep) {
        this.afterDep = afterDep;
    }

    public String getBefJobLvl() {
        return befJobLvl;
    }

    public void setBefJobLvl(String befJobLvl) {
        this.befJobLvl = befJobLvl;
    }

    public String getAfterJobLvl() {
        return afterJobLvl;
    }

    public void setAfterJobLvl(String afterJobLvl) {
        this.afterJobLvl = afterJobLvl;
    }

    public String getBefPos() {
        return befPos;
    }

    public void setBefPos(String befPos) {
        this.befPos = befPos;
    }

    public String getAfterPos() {
        return afterPos;
    }

    public void setAfterPos(String afterPos) {
        this.afterPos = afterPos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}