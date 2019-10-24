package com.cn.ncvt.controller.system;

import com.cn.ncvt.biz.JobLevelBiz;
import com.cn.ncvt.biz.PositionBiz;
import com.cn.ncvt.entity.JobLevel;
import com.cn.ncvt.entity.Position;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : V1.0
 * @ClassName: SystemBasicController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/24 10:40
 **/

@RestController
@RequestMapping("/system/basic")
@Api(tags = "基础设置")
public class SystemBasicController {

    @Autowired
    JobLevelBiz jobLevelBiz;

    @Autowired
    PositionBiz positionBiz;

    @GetMapping("/jobLvlList")
    @ApiOperation(value = "获取职称列表", notes = "page为当前页码, limit为每页记录数量, name作为条件进行模糊查询")
    public Result getJobLvlList(Integer page, Integer limit, String name){
        return jobLevelBiz.getAllJobLvl(page, limit, name);
    }

    @PostMapping("/jobLvl")
    @ApiOperation(value = "新增职称", notes = "传入jobLevel对象")
    public Result addNewJobLvl(@RequestBody JobLevel jobLevel){
        return jobLevelBiz.addNewJobLvl(jobLevel);
    }

    @PutMapping("/jobLvl")
    @ApiOperation(value = "修改职称信息", notes = "传入jobLevel对象")
    public Result updateJobLvl(@RequestBody JobLevel jobLevel){
        return jobLevelBiz.updateJobLvl(jobLevel);
    }

    @DeleteMapping("/jobLvl/{id}")
    @ApiOperation(value = "修改职称信息", notes = "通过id删除")
    public Result deleteJobLvl(@PathVariable Integer id){
        return jobLevelBiz.deleteJobLvl(id);
    }

    @GetMapping("/positionList")
    @ApiOperation(value = "获取职位列表", notes = "page为当前页码, limit为每页记录数量, name作为条件进行模糊查询")
    public Result getPositionList(Integer page, Integer limit, String name){
        return positionBiz.getAllPosition(page, limit, name);
    }

    @PostMapping("/position")
    @ApiOperation(value = "新增职位", notes = "传入Position对象")
    public Result addNewPosition(@RequestBody Position position){
        return positionBiz.addNewPosition(position);
    }

    @PutMapping("/position")
    @ApiOperation(value = "修改职位信息", notes = "传入Position对象")
    public Result updatePosition(@RequestBody Position position){
        return positionBiz.updatePosition(position);
    }

    @DeleteMapping("/position/{id}")
    @ApiOperation(value = "修改职位信息", notes = "通过id删除")
    public Result deletePosition(@PathVariable Integer id){
        return positionBiz.deletePosition(id);
    }

}
