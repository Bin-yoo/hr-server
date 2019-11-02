package com.cn.ncvt.entity;

/**
 * @version : V1.0
 * @ClassName: EmployeeSalary
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:22
 **/
public class EmployeeSalary {

    private int id;
    private int eid;
    private double baseSalary;
    private double meritSalary;
    private Employee employee;

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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getMeritSalary() {
        return meritSalary;
    }

    public void setMeritSalary(double meritSalary) {
        this.meritSalary = meritSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
