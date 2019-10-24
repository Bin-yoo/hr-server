package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.EmployeeAssessmentBiz;
import com.cn.ncvt.entity.EmployeeAssessment;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: EmployeeAssessmentController
 * @Auther: Ashe
 * @Date: 2019/10/23
 **/
@RestController
@RequestMapping("/employeeassessment")
@Api(tags = "员工考核状况")
public class EmployeeAssessmentController {
    @Autowired
    EmployeeAssessmentBiz employeeAssessmentBiz;

    @GetMapping("/allEmployeeAssessment")
    @ApiOperation(value = "查看所有提交上来的考核", notes = "返回全部考核")
    public Result allEmployeeFile(int page, int limit){
        return employeeAssessmentBiz.getAllAssessmentFile(page, limit);
    }

    @PostMapping("/addEmployeeAssessment")
    @ApiOperation(value = "添加员工考核信息", notes = "添加考核项目")
    public Result addAssessmentFile(@RequestBody EmployeeAssessment employeeAssessment){
        return employeeAssessmentBiz.addEmployeeAssessmentFile(employeeAssessment);
    }

    @PostMapping("/updateEmployeeAssessment")
    @ApiOperation(value = "修改考核项目", notes = "修改考核")
    public Result updateAssessmentFile(@RequestBody EmployeeAssessment employeeAssessment){
        return employeeAssessmentBiz.updateEmployeeAssessmentFile(employeeAssessment);
    }

    @DeleteMapping("/deleteEmployeeAssessment/{id}")
    @ApiOperation(value = "删除考核项目", notes = "删除考核项目")
    public Result deleteAssessmentFile(@PathVariable Integer id){
        return employeeAssessmentBiz.deleteEmployeeAssessmentFile(id);
    }
}
