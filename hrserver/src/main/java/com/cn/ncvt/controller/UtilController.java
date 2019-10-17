package com.cn.ncvt.controller;

import com.cn.ncvt.biz.MenuBiz;
import com.cn.ncvt.entity.Menu;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version : V1.0
 * @ClassName: UtilController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/12 14:38
 **/
@RestController
@Api(tags = "工具类")
public class UtilController {

    @Autowired
    MenuBiz menuBiz;

    @GetMapping("/sysmenu")
    @ApiOperation(value = "获取菜单", notes = "根据登录用户的角色权限,获取相应菜单")
    public Result sysmenu() {
        return menuBiz.getMenusByHrId();
    }
}
