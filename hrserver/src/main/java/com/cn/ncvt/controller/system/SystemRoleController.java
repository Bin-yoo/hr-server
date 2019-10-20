package com.cn.ncvt.controller.system;

import com.alibaba.fastjson.JSON;
import com.cn.ncvt.biz.MenuBiz;
import com.cn.ncvt.biz.MenuRoleBiz;
import com.cn.ncvt.biz.RoleBiz;
import com.cn.ncvt.entity.MenuTreeUtil;
import com.cn.ncvt.entity.Role;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: SystemRoleController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/14 15:56
 **/
@RestController
@RequestMapping("/system/role")
@Api(tags = "系统设置")
public class SystemRoleController {

    @Autowired
    RoleBiz roleBiz;

    @Autowired
    MenuBiz menuBiz;

    @Autowired
    MenuRoleBiz menuRoleBiz;

    @GetMapping("/roles")
    @ApiOperation(value = "角色列表", notes = "返回全部角色列表")
    public Result allRoles() {
        return roleBiz.roles();
    }

    @GetMapping("/menuTree/{rid}")
    @ApiOperation(value = "获取角色的权限", notes = "通过rid来获取相应角色的权限")
    public Result menuTree(@PathVariable int rid) {
        return menuBiz.getMenuTreeByRoleId(rid);
    }

    @PostMapping("/role")
    @ApiOperation(value = "添加新角色", notes = "通过rid来获取相应角色的权限")
    public Result addRole(@RequestBody Role role) {
        return roleBiz.addNewRole(role.getName(),role.getNameZh());
    }

    @PutMapping("/updateMenuTree")
    @ApiOperation(value = "更新角色权限", notes = "传入参数rid和勾选节点的id数组,进行更新")
    public Result updateMenuTree(@RequestBody MenuTreeUtil menuTreeUtil) {
        //注释的代码不要删
        //@RequestBody String object
        /*int rid = Integer.parseInt(JSON.parseObject(object).get("rid").toString());
        String key1 = JSON.parseObject(object).get("checkKeys").toString();
        System.out.println(key1);
        int[] checkKeys = null;
        if (!key1.equals("[]")){
            String key2 = key1.substring(1);
            String key3 = key2.substring(0,key2.length()-1);
            String[] keyArray = key3.split(",");

            checkKeys = new int[keyArray.length];
            for(int i=0; i < keyArray.length; i++)
            {
                checkKeys[i] = Integer.parseInt(keyArray[i]);
            }
        } else {
            checkKeys = new int[0];
        }
        System.out.println(checkKeys.length);
        if (checkKeys.length == 0) {
            System.out.println("弟中弟");
        }*/
        return menuRoleBiz.updateMenuTreeByRid(menuTreeUtil);
    }

    @DeleteMapping("/role/{rid}")
    @ApiOperation(value = "删除角色", notes = "通过rid来删除角色,及其关联的角色权限")
    public Result updateMenuTree(@PathVariable int rid) {
        return roleBiz.deleteRoleByRid(rid);
    }
}
