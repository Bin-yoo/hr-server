package com.cn.ncvt.controller;

import com.cn.ncvt.biz.EmployeeBiz;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version : V1.0
 * @ClassName: EmployeeController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:44
 **/
@RestController
@RequestMapping("/employee")
@Api(tags = "员工档案")
public class EmployeeController {

    @Autowired
    EmployeeBiz employeeBiz;

    @GetMapping("/allEmp")
    @ApiOperation(value = "获取全部员工档案", notes = "page为当前页,limit为记录每页数量")
    public Result allEmployeeFile(int page, int limit){
        return employeeBiz.getAllEmployeeFile(page, limit);
    }

}
