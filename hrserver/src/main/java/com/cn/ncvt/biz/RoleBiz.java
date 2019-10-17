package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Role;
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
}
