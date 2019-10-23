package com.cn.ncvt.controller;

import com.cn.ncvt.biz.AssessmentBiz;
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

    @GetMapping("/allAssesment")
    @ApiOperation(value = "查看所有考核", notes = "返回全部考核")
    public Result allEmployeeFile(int page, int limit){
        return assessmentBiz.getAllAssessmentFile(page, limit);
    }

    /**
     *
     * @param assessment
     * @return
     */
    @PostMapping("/addAssesment")
    @ApiOperation(value = "查看所有考核", notes = "返回全部考核")
    public Result addAssessmentFile(@RequestBody Assessment assessment){
        return assessmentBiz.addAssessmentFile(assessment);
    }

    /**
     *
     * @param assessment
     * @return
     */
    @PostMapping("/updateAssesment")
    @ApiOperation(value = "修改考核项目", notes = "修改考核")
    public Result updateAssessmentFile(@RequestBody Assessment assessment){
        return assessmentBiz.updateAssessmentFile(assessment);
    }


    @DeleteMapping("/deleteAssesment/{id}")
    @ApiOperation(value = "删除考核项目", notes = "删除考核项目")
    public Result deleteAssessmentFile(@PathVariable Integer id){
        return assessmentBiz.deleteAssessmentFile(id);
    }


}
