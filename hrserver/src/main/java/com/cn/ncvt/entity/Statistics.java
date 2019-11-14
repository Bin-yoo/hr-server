package com.cn.ncvt.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @version : V1.0
 * @ClassName: Statistics
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/13 15:23
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Statistics {

    private Long value;
    private String name;
    private String proportion;

    private String time;
    private Long rewards;
    private Long punishments;

    public Statistics() {
    }

    public Statistics(Long value, String name, String proportion) {
        this.value = value;
        this.name = name;
        this.proportion = proportion;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getRewards() {
        return rewards;
    }

    public void setRewards(Long rewards) {
        this.rewards = rewards;
    }

    public Long getPunishments() {
        return punishments;
    }

    public void setPunishments(Long punishments) {
        this.punishments = punishments;
    }
}
