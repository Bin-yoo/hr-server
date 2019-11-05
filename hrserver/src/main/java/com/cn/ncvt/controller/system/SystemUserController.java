package com.cn.ncvt.controller.system;

import com.cn.ncvt.biz.UserBiz;
import com.cn.ncvt.entity.User;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: SystemUserController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/5 19:44
 **/
@RestController
@RequestMapping("/system/user")
@Api(tags = "用户管理")
public class SystemUserController {

    @Autowired
    UserBiz userBiz;

    @GetMapping("/userlist")
    @ApiOperation(value = "获取系统用户列表", notes = "")
    public Result getUserList(){
        return userBiz.getAllUserList();
    }

    @PostMapping("/user")
    @ApiOperation(value = "删除系统用户账号", notes = "根据用户id删除账号")
    public Result addNewUser(@RequestBody User user){
        return userBiz.addNewUser(user);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除系统用户账号", notes = "根据用户id删除账号")
    public Result deleteUser(@PathVariable Integer id){
        return userBiz.deleteUser(id);
    }

}
