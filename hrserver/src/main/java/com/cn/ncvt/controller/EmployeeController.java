package com.cn.ncvt.controller;

import com.cn.ncvt.biz.EmployeeBiz;
import com.cn.ncvt.entity.Employee;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: EmployeeController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:44
 **/
@RestController
@RequestMapping("/employee")
@Api(tags = "员工资料")
public class EmployeeController {

    @Autowired
    EmployeeBiz employeeBiz;

    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/allEmp")
    @ApiOperation(value = "查看员工资料", notes = "返回全部员工资料")
    public Result allEmployeeFile(int page, int limit){
        return employeeBiz.getAllEmployeeFile(page, limit);
    }

    /**
     * 添加员工
     * @param employee 员工资料
     * @return
     */
    @PostMapping("/addEmp")
    @ApiOperation(value = "添加员工资料", notes = "将员工资料添加进数据库")
    public Result addEmployeeFile(@RequestBody Employee employee){
        return employeeBiz.addEmployeeFile(employee);
    }

    /**
     * 更新员工资料
     * @param employee 员工资历
     * @return
     */
    @PutMapping("/updateEmp")
    @ApiOperation(value = "修改员工资料", notes = "修改员工资料")
    public Result updateEmployeeFile(@RequestBody Employee employee){
        return employeeBiz.updateEmployeeFile(employee);
    }

    /**
     * 通过id删除员工资料
     * @param id 员工id
     * @return
     */
    @DeleteMapping("/deleteEmp/{id}")
    @ApiOperation(value = "删除员工资料", notes = "删除员工资料")
    public Result deleteByIdEmployeeFile(@PathVariable Integer id){
        return employeeBiz.deleteByIdEmployeeFile(id);
    }
}
