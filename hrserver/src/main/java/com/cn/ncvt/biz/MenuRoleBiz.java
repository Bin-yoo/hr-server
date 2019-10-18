package com.cn.ncvt.biz;

import com.cn.ncvt.entity.MenuTreeUtil;
import com.cn.ncvt.mapper.MenuRoleMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version : V1.0
 * @ClassName: MenuRoleBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/18 14:50
 **/
@Service
public class MenuRoleBiz {

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public Result updateMenuTreeByRid(MenuTreeUtil menuTreeUtil) {
        menuRoleMapper.deleteByRid(menuTreeUtil.getRid());
        try {
            //判断传到后台的勾选节点数组长度是否为0,如为0则不进行添加操作
            if (menuTreeUtil.getCheckedKeys().length != 0) {
                menuRoleMapper.addMenuRole(menuTreeUtil.getRid(), menuTreeUtil.getCheckedKeys());
                return ResultFactory.buildSuccessResult("更新权限成功");
            } else {
                return ResultFactory.buildSuccessResult("更新权限成功");
            }
        } catch (Exception e) {
            return ResultFactory.buildFailResult("更新失败");
        }
    }
}
