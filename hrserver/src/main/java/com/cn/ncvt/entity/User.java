package com.cn.ncvt.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: User
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/9/22 16:55
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class User {

    private Integer id;
    private Integer eid;
    private String name;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String oldPassword;
    private String salt;
    private String userface;
    private boolean enabled = true;
    private String remark;
    private List<Role> roles;
    private int[] rolesKey;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int[] getRolesKey() {
        return rolesKey;
    }

    public void setRolesKey(int[] rolesKey) {
        this.rolesKey = rolesKey;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
