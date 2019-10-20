package com.cn.ncvt.controller;

import com.cn.ncvt.biz.UserBiz;
import com.cn.ncvt.entity.User;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户登陆或退出")
public class LoginController {

    @Autowired
    UserBiz userBiz;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "由于axios是json方式传输数据,所以传到后台的是一个json用户对象,对象里包含用户名(userName)和密码(password)即可")
    public Result login(@RequestBody User user){
        return userBiz.userLogin(user);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "退出", notes = "请求该链接,后台会清除相应session")
    public Result logout(){
        return ResultFactory.buildSuccessResult("退出成功");
    }

    @GetMapping("/unauthorized")
    @ApiOperation(value = "未登录或token过期", notes = "后台会鉴别用户状态或token,返回提示")
    public Result unauthorized(){
        return ResultFactory.buildResult(401,true,"用户未登录或token过期",null);
    }

}
