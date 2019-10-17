package com.cn.ncvt.controller;

import com.cn.ncvt.biz.MenuBiz;
import com.cn.ncvt.biz.RoleBiz;
import com.cn.ncvt.entity.Role;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: SystemController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/14 15:56
 **/
@RestController
@RequestMapping("/system")
@Api(tags = "系统设置")
public class SystemController {

    @Autowired
    RoleBiz roleBiz;

    @Autowired
    MenuBiz menuBiz;

    @GetMapping("/basic/roles")
    @ApiOperation(value = "角色列表", notes = "返回全部角色列表")
    public Result allRoles() {
        return roleBiz.roles();
    }

    @GetMapping("/basic/menuTree/{rid}")
    @ApiOperation(value = "获取角色的权限", notes = "通过rid来获取相应角色的权限")
    public Result menuTree(@PathVariable int rid) {
        return menuBiz.getMenuTreeByRoleId(rid);
    }

    @PostMapping("/basic/role")
    @ApiOperation(value = "添加新角色", notes = "通过rid来获取相应角色的权限")
    public Result addRole(@RequestBody Role role) {
        return roleBiz.addNewRole(role.getName(),role.getNameZh());
    }
}
