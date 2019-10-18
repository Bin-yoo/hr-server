package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Role;
import com.cn.ncvt.mapper.MenuRoleMapper;
import com.cn.ncvt.mapper.RoleMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: RoleBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/14 15:58
 **/
@Service
public class RoleBiz {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public Result roles() {
        List<Role> roleList = roleMapper.roles();
        if (roleList != null)
            return ResultFactory.buildSuccessResult(roleList);
        else
            return ResultFactory.buildFailResult("获取失败");
    }

    public Result addNewRole(String role, String roleZh) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        try {
            roleMapper.addNewRole(role,roleZh);
            return ResultFactory.buildSuccessResult("添加成功");
        } catch (Exception e){
            return ResultFactory.buildFailResult("添加失败");
        }
    }

    public Result deleteRoleByRid(int rid) {
        try {
            menuRoleMapper.deleteByRid(rid);    //数据库表不设置级联,所以要先删除关联的菜单权限表中数据
            roleMapper.deleteRoleById(rid);
            return ResultFactory.buildSuccessResult("删除成功");
        } catch (Exception e) {
            return ResultFactory.buildFailResult("删除失败");
        }
    }
}
