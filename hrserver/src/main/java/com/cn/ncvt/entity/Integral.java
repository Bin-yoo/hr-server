package com.cn.ncvt.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: Integral
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:24
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class Integral {

    private int id;
    private int eid;
    private int integral;
    private Employee employee;
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
