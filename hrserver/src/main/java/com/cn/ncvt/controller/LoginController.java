package com.cn.ncvt.controller;

import com.cn.ncvt.biz.HrBiz;
import com.cn.ncvt.entity.Hr;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: LoginController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/9/24 16:53
 **/
@RestController
public class LoginController {

    @Autowired
    HrBiz hrBiz;

    @PostMapping("/login")
    public Result login(@RequestBody Hr hr){
        return hrBiz.userLogin(hr);
    }

    @GetMapping("/logout")
    public Result logout(){
        System.out.println("退出");
        return ResultFactory.buildSuccessResult("退出成功");
    }

    @GetMapping("/unauthorized")
    public Result unauthorized(){
        return ResultFactory.buildResult(401,true,"用户未登录",null);
    }

    @GetMapping("/check")
    public void check(){
        System.out.println("check开始:"+SecurityUtils.getSubject().getPrincipal());
        System.out.println("checkSession:"+SecurityUtils.getSubject().getSession().getId());
    }
}
