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

    private Integer id;
    private Integer eid;
    private Integer aid;
    private String data;
    private String remark;
    private String result;
    private Boolean isCheck;

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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
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
