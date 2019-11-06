package com.cn.ncvt.entity;

import java.util.Date;

public class SalaryLog {
    private Integer id;

    private Integer eid;

    private Double befBaseSalary;

    private Double afterBaseSalary;

    private Double befMeritSalary;

    private Double afterMeritSalary;

    private Date date;

    private String remark;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public Double getBefBaseSalary() {
        return befBaseSalary;
    }

    public void setBefBaseSalary(Double befBaseSalary) {
        this.befBaseSalary = befBaseSalary;
    }

    public Double getAfterBaseSalary() {
        return afterBaseSalary;
    }

    public void setAfterBaseSalary(Double afterBaseSalary) {
        this.afterBaseSalary = afterBaseSalary;
    }

    public Double getBefMeritSalary() {
        return befMeritSalary;
    }

    public void setBefMeritSalary(Double befMeritSalary) {
        this.befMeritSalary = befMeritSalary;
    }

    public Double getAfterMeritSalary() {
        return afterMeritSalary;
    }

    public void setAfterMeritSalary(Double afterMeritSalary) {
        this.afterMeritSalary = afterMeritSalary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}