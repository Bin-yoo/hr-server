package com.cn.ncvt.controller;

import com.cn.ncvt.biz.MenuBiz;
import com.cn.ncvt.entity.Menu;
import com.cn.ncvt.result.Result;
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
public class UtilController {

    @Autowired
    MenuBiz menuBiz;

    @GetMapping("/sysmenu")
    public Result sysmenu() {
        return menuBiz.getMenusByHrId();
    }
}
