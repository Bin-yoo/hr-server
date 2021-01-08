package com.cn.ncvt.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

/**
 * @version : V1.0
 * @ClassName: Employee
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 15:52
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class Employee {
    private Integer id;
    @Excel(name = "工号")
    private Long workId;
    @Excel(name = "员工姓名")
    private String name;
    @Excel(name = "性别")
    private String sex;
    private Integer nationId;
    @Excel(name = "民族")
    private String nationName;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "出生日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp birthday;
    private Integer politiclId;
    @Excel(name = "政治面貌")
    private String politiclName;
    @Excel(name = "婚姻状况")
    private String wedlock;
    @Excel(name = "籍贯")
    private String nativePlace;
    @Excel(name = "身份证号码")
    private String idCard;
    @Excel(name = "邮件地址")
    private String email;
    @Excel(name = "联系电话")
    private String phone;
    @Excel(name = "居住地址")
    private String address;
    private Integer departmentId;
    @Excel(name = "所属部门")
    private String departmentName;
    private Integer positionId;
    @Excel(name = "职位")
    private String positionName;
    private Integer jobLevelId;
    @Excel(name = "职称")
    private String jobLevelName;
    @Excel(name = "最高学历")
    private String titopDegree;
    @Excel(name = "所学专业")
    private String specialty;
    @Excel(name = "毕业院校")
    private String school;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "入职日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp beginDate;
    @Excel(name = "在职状态")
    private String workState;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "入职日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp conversionTime;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "入职日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp quitTime;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "入职日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp beginContract;
    @JsonFormat(pattern = "yyyy-MM-dd",  timezone="GMT+8")
    @Excel(name = "入职日期", databaseFormat = "yyyy-MM-dd")
    private Timestamp endContract;
    private String picture;
    private double baseSalary;

    private Timestamp transferdate;

    private String commit;
    private Integer eaid;

    private String password;
    private String salt;


    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
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

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
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

    public Integer getPoliticlId() {
        return politiclId;
    }

    public void setPoliticlId(Integer politiclId) {
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Integer jobLevelId) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Timestamp getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Timestamp transferdate) {
        this.transferdate = transferdate;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Integer getEaid() {
        return eaid;
    }

    public void setEaid(Integer eaid) {
        this.eaid = eaid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
