package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.AssessmentBiz;
import com.cn.ncvt.biz.EmployeeAssessmentBiz;
import com.cn.ncvt.entity.Assessment;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: AssessmentController
 * @Auther: Ashe
 * @Date: 2019/10/23
 **/
@RestController
@RequestMapping("/assessment")
@Api(tags = "考核")
public class AssessmentController {
    @Autowired
    AssessmentBiz assessmentBiz;
    @Autowired
    EmployeeAssessmentBiz employeeAssessmentBiz;

    @GetMapping("/allAssesment")
    @ApiOperation(value = "查看所有考核", notes = "返回全部考核")
    public Result allEmployeeFile(Integer page, Integer limit, String state, String name, Integer did){
        return assessmentBiz.getAllAssessmentFile(page,limit,state,name,did);
    }

    /**
     *
     * @param assessment
     * @return
     */
    @PostMapping("/addAssesment")
    @ApiOperation(value = "添加考核项目", notes = "添加考核项目")
    public Result addAssessmentFile(@RequestBody Assessment assessment){
        return assessmentBiz.addAssessmentFile(assessment);
    }

    /**
     *
     * @param assessment
     * @return
     */
    @PutMapping("/updateAssesment")
    @ApiOperation(value = "修改考核项目", notes = "修改考核")
    public Result updateAssessmentFile(@RequestBody Assessment assessment){
        return assessmentBiz.updateAssessmentFile(assessment);
    }

    @PutMapping("/updateAssesmentState")
    @ApiOperation(value = "更新考核状态", notes = "修改考核状态")
    public Result updateAssesmentState(@RequestBody Assessment assessment){
        return assessmentBiz.updateAssessmentState(assessment);
    }


    @DeleteMapping("/deleteAssesment/{id}")
    @ApiOperation(value = "删除考核项目", notes = "删除考核项目")
    public Result deleteAssessmentFile(@PathVariable Integer id){
        return assessmentBiz.deleteAssessmentFile(id);
    }

    @GetMapping("/{aid}/empAssessment")
    @ApiOperation(value = "查看某考核项目的考核资料", notes = "这个是查看某考核任务下,所有员工提交的考核资料")
    public Result allEmployeeAssessmentFile(Integer page, Integer limit, @PathVariable("aid") Integer aid, String name){
        return employeeAssessmentBiz.getAssessmentFileByAID(page,limit,aid,name);
    }

    @GetMapping("/empAssessment/{id}")
    @ApiOperation(value = "查看考核下某员工提交的考核资料", notes = "这个是查看某考核任务下某员工提交的考核资料")
    public Result viewEmployeeAssessmentFile(@PathVariable("id") Integer id){
        return employeeAssessmentBiz.getEmpAssessmentFileByID(id);
    }

    @GetMapping("/empAllAssessment/{eid}")
    @ApiOperation(value = "查看某员工的考核", notes = "")
    public Result viewEmployeeAllAssessment(Integer page, Integer limit, @PathVariable("eid") Integer eid){
        return employeeAssessmentBiz.getEmpAllAssessment(page, limit, eid);
    }
}
