package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.EmployeeAssessmentBiz;
import com.cn.ncvt.entity.EmployeeAssessment;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.cn.ncvt.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: EmployeeAssessmentController
 * @Auther: Ashe
 * @Date: 2019/10/23
 **/
@RestController
@RequestMapping("/empAssessment")
@Api(tags = "员工考核（我的考核）")
public class EmployeeAssessmentController {
    @Autowired
    EmployeeAssessmentBiz employeeAssessmentBiz;

    @GetMapping("/myAssessment")
    @ApiOperation(value = "个人考核", notes = "")
    public Result getMyAssessment(Integer page, Integer limit, String state, String name){
        return employeeAssessmentBiz.getMyAssessment(page,limit,state,name);
    }

    @GetMapping("/allEmpAssessment/{aid}")
    @ApiOperation(value = "员工考核", notes = "通过aid来查询这个考核中所有员工提交的考核")
    public Result allEmployeeFile(@PathVariable Integer aid,Integer page, Integer limit){
        return employeeAssessmentBiz.getAllAssessmentFile(aid,page,limit);
    }

    @PostMapping("/addEmployeeAssessment")
    @ApiOperation(value = "添加员工考核信息", notes = "提交考核，不要给result,isCheck传参")
    public Result addAssessmentFile(@RequestBody EmployeeAssessment employeeAssessment){
        return employeeAssessmentBiz.addEmployeeAssessmentFile(employeeAssessment);
    }

    @PostMapping("/updateEmployeeAssessment")
    @ApiOperation(value = "修改考核项目", notes = "选择需要的字段修改，比如说只修改审批状态")
    public Result updateAssessmentFile(@RequestBody EmployeeAssessment employeeAssessment){
        return employeeAssessmentBiz.updateEmployeeAssessmentFile(employeeAssessment);
    }

    @DeleteMapping("/deleteEmployeeAssessment/{id}")
    @ApiOperation(value = "删除考核项目", notes = "删除考核项目")
    public Result deleteAssessmentFile(@PathVariable Integer id){
        return employeeAssessmentBiz.deleteEmployeeAssessmentFile(id);
    }

    @PostMapping("/data")
    @ApiOperation(value = "上传考核附件", notes = "")
    public Result uploadData(@RequestParam("picture") MultipartFile picture){
        UploadUtil uploadUtil = new UploadUtil();
        return ResultFactory.buildSuccessResult(uploadUtil.upload(picture, "assessment"));
    }

    @GetMapping("/myAssessment/{aid}")
    @ApiOperation(value = "查看自己的某项目提交的考核材料", notes = "")
    public Result viewMyAssessment(@PathVariable Integer aid){
        return employeeAssessmentBiz.viewMyAssessment(aid);
    }
}
