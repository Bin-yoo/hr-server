package com.cn.ncvt.entity;

/**
 * @version : V1.0
 * @ClassName: Statistics
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/13 15:23
 **/
public class Statistics {

    private Long value;
    private String name;
    private String proportion;

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
}
