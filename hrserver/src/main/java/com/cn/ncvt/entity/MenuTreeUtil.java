package com.cn.ncvt.entity;

/**
 * @version : V1.0
 * @ClassName: MenuTreeUtil
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/17 23:47
 **/
public class MenuTreeUtil {

    private Integer rid;
    private int[] checkedKeys;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public int[] getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(int[] checkedKeys) {
        this.checkedKeys = checkedKeys;
    }
}
