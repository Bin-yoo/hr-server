package com.cn.ncvt.controller.system;

import com.cn.ncvt.biz.DepartmentBiz;
import com.cn.ncvt.biz.JobLevelBiz;
import com.cn.ncvt.biz.PositionBiz;
import com.cn.ncvt.entity.Department;
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

    @Autowired
    DepartmentBiz departmentBiz;

    @GetMapping("/jobLvlList")
    @ApiOperation(value = "获取职称列表", notes = "page为当前页,limit为记录每页数量,name为查询条件")
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
    @ApiOperation(value = "删除职称信息", notes = "通过id删除")
    public Result deleteJobLvl(@PathVariable Integer id){
        return jobLevelBiz.deleteJobLvl(id);
    }

    @GetMapping("/positionList")
    @ApiOperation(value = "获取职位列表", notes = "page为当前页,limit为记录每页数量,name为查询条件")
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
    @ApiOperation(value = "删除职位信息", notes = "通过id删除")
    public Result deletePosition(@PathVariable Integer id){
        return positionBiz.deletePosition(id);
    }

    @GetMapping("/department/list")
    @ApiOperation(value = "部门列表", notes = "获取部门列表")
    public Result getDepList(){
        return departmentBiz.getDepTree();
    }

    @PostMapping("/department")
    @ApiOperation(value = "添加部门", notes = "传入部门对象,parentId为主要值")
    public Result addDepartment(@RequestBody Department department){
        return departmentBiz.addDepartment(department);
    }

    @PutMapping("/department")
    @ApiOperation(value = "修改部门信息", notes = "传入部门对象,parentId为主要值")
    public Result updateDepartment(@RequestBody Department department){
        return departmentBiz.updateDepartment(department);
    }

    @DeleteMapping("/department/{id}")
    @ApiOperation(value = "删除部门", notes = "传入部门ID")
    public Result deleteDepartment(@PathVariable Integer id){
        return departmentBiz.deleteDepartment(id);
    }
}