package com.cn.ncvt.controller;

import com.cn.ncvt.biz.MenuBiz;
import com.cn.ncvt.entity.Menu;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
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
        return menuBiz.getMenusByUserId();
    }

    @GetMapping("/addEmpFile")
    @ApiOperation(value = "检查添加档案权限", notes = "")
    public Result addEmpFile(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/viewEmpFile")
    @ApiOperation(value = "查看", notes = "")
    public Result viewEmpFile(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/modifyEmpFile")
    @ApiOperation(value = "修改档案", notes = "")
    public Result modifyEmpFile(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/deleteEmpFile")
    @ApiOperation(value = "删除档案", notes = "")
    public Result deleteEmpFile(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/viewSalaryLog")
    @ApiOperation(value = "查看调薪记录", notes = "")
    public Result viewSalaryLog(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/updateSalary")
    @ApiOperation(value = "调薪", notes = "")
    public Result updateSalary(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/addAssessment")
    @ApiOperation(value = "添加考核", notes = "")
    public Result addAssessment(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/changeAssState")
    @ApiOperation(value = "更改考核状态", notes = "")
    public Result changeAssState(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/reviewAssessment")
    @ApiOperation(value = "审核考核", notes = "")
    public Result reviewAssessment(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/modifyAssessment")
    @ApiOperation(value = "编辑考核", notes = "")
    public Result modifyAssessment(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/deleteAssessment")
    @ApiOperation(value = "删除考核", notes = "")
    public Result deleteAssessment(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/addRp")
    @ApiOperation(value = "添加奖惩", notes = "")
    public Result addRp(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/updateRp")
    @ApiOperation(value = "修改奖惩", notes = "")
    public Result updateRp(){
        return ResultFactory.buildSuccessResult("ok");
    }
    @GetMapping("/deleteRp")
    @ApiOperation(value = "删除奖惩", notes = "")
    public Result deleteRp(){
        return ResultFactory.buildSuccessResult("ok");
    }
}
