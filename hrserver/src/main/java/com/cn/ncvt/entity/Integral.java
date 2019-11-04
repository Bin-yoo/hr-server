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

    private Integer id;
    private Integer eid;
    private Integer integral;
    private Employee employee;
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
