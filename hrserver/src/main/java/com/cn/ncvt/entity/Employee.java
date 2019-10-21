package com.cn.ncvt.entity;

import java.sql.Timestamp;

/**
 * @version : V1.0
 * @ClassName: Employee
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 15:52
 **/
public class Employee {

    private int id;
    private int workId;
    private String name;
    private String sex;
    private int nationId;
    private String nationName;
    private Timestamp birthday;
    private int politiclId;
    private String politiclName;
    private String wedlock;
    private String nativePlace;
    private String idCard;
    private String email;
    private String phone;
    private String address;
    private int departmentId;
    private String departmentName;
    private int jobLevelId;
    private String jobLevelName;
    private String titopDegree;
    private String specialty;
    private String school;
    private Timestamp beginDate;
    private String workState;
    private Timestamp conversionTime;
    private Timestamp quitTime;
    private Timestamp beginContract;
    private Timestamp endContract;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public int getPoliticlId() {
        return politiclId;
    }

    public void setPoliticlId(int politiclId) {
        this.politiclId = politiclId;
    }

    public String getPoliticlName() {
        return politiclName;
    }

    public void setPoliticlName(String politiclName) {
        this.politiclName = politiclName;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(int jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    public String getTitopDegree() {
        return titopDegree;
    }

    public void setTitopDegree(String titopDegree) {
        this.titopDegree = titopDegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public Timestamp getConversionTime() {
        return conversionTime;
    }

    public void setConversionTime(Timestamp conversionTime) {
        this.conversionTime = conversionTime;
    }

    public Timestamp getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Timestamp quitTime) {
        this.quitTime = quitTime;
    }

    public Timestamp getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(Timestamp beginContract) {
        this.beginContract = beginContract;
    }

    public Timestamp getEndContract() {
        return endContract;
    }

    public void setEndContract(Timestamp endContract) {
        this.endContract = endContract;
    }
}
