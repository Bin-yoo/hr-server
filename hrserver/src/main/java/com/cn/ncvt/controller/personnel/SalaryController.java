package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.SalaryBiz;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SalaryController
 * @Auther: Ashe
 * @Date: 2019/10/31
 **/
@RestController
@RequestMapping("/salary")
@Api(tags = "员工工资")
public class SalaryController {
    @Autowired
    SalaryBiz salaryBiz;
    @GetMapping("/allSalary")
    @ApiOperation(value = "查看所有员工的工资", notes = "")
    public Result allIntegralFile(int page, int limit){
        return salaryBiz.getAllSalaryFile(page, limit);
    }
}
