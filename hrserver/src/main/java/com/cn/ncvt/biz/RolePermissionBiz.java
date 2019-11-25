package com.cn.ncvt.biz;

import com.cn.ncvt.entity.MenuTreeUtil;
import com.cn.ncvt.mapper.RolePermissionMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version : V1.0
 * @ClassName: RolePermissionBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/25 20:23
 **/

@Service
public class RolePermissionBiz {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public Result updatePermissionTreeByRid(MenuTreeUtil menuTreeUtil) {
        rolePermissionMapper.deleteByRid(menuTreeUtil.getRid());
        try {
            //判断传到后台的勾选节点数组长度是否为0,如为0则不进行添加操作
            if (menuTreeUtil.getCheckedKeys().length != 0) {
                rolePermissionMapper.addRolePermission(menuTreeUtil.getRid(), menuTreeUtil.getCheckedKeys());
                return ResultFactory.buildSuccessResult("更新权限成功");
            } else {
                return ResultFactory.buildSuccessResult("更新权限成功");
            }
        } catch (Exception e) {
            return ResultFactory.buildFailResult("更新失败");
        }
    }
}
