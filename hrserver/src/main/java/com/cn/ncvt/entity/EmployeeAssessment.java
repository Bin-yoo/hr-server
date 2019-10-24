package com.cn.ncvt.entity;

import java.sql.Timestamp;

/**
 * @version : V1.0
 * @ClassName: EmployeeAssessment
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:19
 **/
public class EmployeeAssessment {

    private int id;
    private int eId;
    private int aId;
    private String data;
    private String remark;
    private String result;
    private Boolean isCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
