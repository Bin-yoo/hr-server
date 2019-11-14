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
    @ApiOperation(value = "获取系统用户列表", notes = "page为当前页,limit为记录每页数量,name为查询条件")
    public Result getUserList(Integer page, Integer limit, String name){
        return userBiz.getAllUserList(page, limit, name);
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "新增账号", notes = "传入用户对象进行新增")
    public Result addNewUser(@RequestBody User user){
        return userBiz.addNewUser(user);
    }

    @PutMapping("/updateUser")
    @ApiOperation(value = "修改用户账号信息", notes = "传入用户对象进行修改")
    public Result updateUser(@RequestBody User user){
        return userBiz.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除系统用户账号", notes = "根据用户id删除账号")
    public Result deleteUser(@PathVariable Integer id){
        return userBiz.deleteUser(id);
    }

    @PutMapping("/enabled")
    @ApiOperation(value = "禁用或启用账号", notes = "根据用户id和enable进行修改")
    public Result changeUserEnabledState(@RequestBody User user){
        return userBiz.changeUserEnabledState(user);
    }

    @PutMapping("/resetpsw")
    @ApiOperation(value = "重置密码", notes = "仅管理员可用")
    public Result resetPassword(@RequestBody User user){
        return userBiz.resetPassword(user);
    }

    @PutMapping("/password")
    @ApiOperation(value = "修改密码", notes = "传入旧密码和新密码")
    public Result updatePassword(@RequestBody User user){
        return userBiz.updatePassword(user);
    }

}
