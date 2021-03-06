package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.SalaryBiz;
import com.cn.ncvt.biz.SalaryLogBiz;
import com.cn.ncvt.entity.EmployeeSalary;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    SalaryLogBiz salary_logBiz;

    @GetMapping("/allEmpSalary")
    @ApiOperation(value = "查看所有员工的工资", notes = "必须要给page,limit传参数，其他的没有强制要求")
    public Result allIntegralFile(Integer page, Integer limit,Integer departmentId,Integer positionId, Integer jobLevelId,String name){
        return salaryBiz.getAllSalaryFile(page, limit,departmentId,positionId,jobLevelId,name);
    }

    @PutMapping("/updateEmpSalary")
    @ApiOperation(value = "修改员工工资", notes = "注意，这里是以员工id来进行修改的")
    public Result updateEmpSalaryFile(@RequestBody EmployeeSalary employeeSalary){
        return salaryBiz.updateEmpSalaryFile(employeeSalary);
    }

    @GetMapping("/befUpdate/{eid}")
    @ApiOperation(value = "初始化修改信息", notes = "")
    public Result befUpdateEmpSalaryFile(@PathVariable Integer eid){
        return salaryBiz.befUpdateEmpSalaryFile(eid);
    }

    @GetMapping("/salary_log/{eid}")
    @ApiOperation(value = "调薪记录", notes = "")
    public Result allIntegralFile(Integer page, Integer limit,@PathVariable Integer eid){
        return salary_logBiz.getAllSalaryLogByIdFile(page, limit,eid);
    }
}
