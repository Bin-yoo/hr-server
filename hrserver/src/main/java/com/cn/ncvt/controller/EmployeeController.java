package com.cn.ncvt.controller;

import com.cn.ncvt.biz.EmployeeBiz;
import com.cn.ncvt.result.Result;
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
public class EmployeeController {

    @Autowired
    EmployeeBiz employeeBiz;

    @GetMapping("/allEmp")
    public Result allEmployeeFile(){
        return employeeBiz.getAllEmployeeFile();
    }

}
