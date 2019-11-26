package com.cn.ncvt.controller;

import com.cn.ncvt.biz.*;
import com.cn.ncvt.entity.Employee;
import com.cn.ncvt.entity.PoliticalStatus;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.cn.ncvt.util.PoiUtil;
import com.cn.ncvt.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: EmployeeController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:44
 **/
@RestController
@RequestMapping("/employee")
@Api(tags = "员工档案")
public class EmployeeController {

    @Autowired
    EmployeeBiz employeeBiz;
    @Autowired
    NationBiz nationBiz;
    @Autowired
    PoliticalStatusBiz politicalStatusBiz;
    @Autowired
    DepartmentBiz departmentBiz;
    @Autowired
    PositionBiz positionBiz;
    @Autowired
    JobLevelBiz jobLevelBiz;

    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/allEmp")
    @ApiOperation(value = "获取全部员工档案", notes = "page为当前页,limit为记录每页数量")
    public Result allEmployeeFile(Integer page, Integer limit, Integer departmentId, Integer positionId, Integer jobLevelId, String name) {
        return employeeBiz.getAllEmployeeFile(page, limit, departmentId, positionId, jobLevelId, name);
    }

    /**
     * 添加员工
     *
     * @param employee 员工资料
     * @return
     */
    @PostMapping("/addEmp")
    @ApiOperation(value = "添加员工资料", notes = "将员工资料添加进数据库")
    public Result addEmployeeFile(@RequestBody Employee employee) {
        return employeeBiz.addEmployeeFile(employee);
    }

    /**
     * 更新员工资料
     *
     * @param employee 员工资历
     * @return
     */
    @PutMapping("/updateEmp")
    @ApiOperation(value = "修改员工资料", notes = "修改员工资料")
    public Result updateEmployeeFile(@RequestBody Employee employee) {
        return employeeBiz.updateEmployeeFile(employee);
    }

    /**
     * 通过id删除员工资料
     *
     * @param id 员工id
     * @return
     */
    @DeleteMapping("/deleteEmp/{id}")
    @ApiOperation(value = "删除员工资料", notes = "删除员工资料")
    public Result deleteByIdEmployeeFile(@PathVariable Integer id) {
        return employeeBiz.deleteByIdEmployeeFile(id);
    }

    @GetMapping("/checkEmp/{id}")
    @ApiOperation(value = "查看某个员工的资料", notes = "")
    public Result selectByIdEmployeeFile(@PathVariable Integer id) {
        return employeeBiz.selectByIdEmployeeFile(id);
    }

    @GetMapping("/init")
    @ApiOperation(value = "初始化各种下拉菜单", notes = "")
    public Result beforeAddEmployeeFile() {
        return employeeBiz.getAllDownMenu();
    }

    @GetMapping("/myfile")
    @ApiOperation(value = "获取个人档案", notes = "")
    public Result getMyFile() {
        return employeeBiz.getMyFile();
    }

    @PostMapping("/picture")
    @ApiOperation(value = "上传员工图片", notes = "")
    public Result uploadUserFace(@RequestParam("picture") MultipartFile picture) {
        UploadUtil uploadUtil = new UploadUtil();
        return ResultFactory.buildSuccessResult(uploadUtil.upload(picture, "emp"));
    }

    @GetMapping("/checkworkid")
    @ApiOperation(value = "检查工号是否重复", notes = "")
    public Result checkWorkID(Integer workId) {
        return employeeBiz.checkWorkID(workId);
    }

    @GetMapping("/empExcel")
    public void excelDownload(HttpServletResponse response) throws IOException {
        List<Employee> list = employeeBiz.outputAllEmpFile();
        if (list == null) {
            return;
        }
        PoiUtil.exportExcel(response, list);
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入员工档案", notes = "")
    public Result importEmp(@RequestParam("emps") MultipartFile file){
        List<Employee> emps = PoiUtil.importExcel(file,
                nationBiz.getAllNations(), politicalStatusBiz.getAllPolitics(),
                departmentBiz.getAllDeps(), positionBiz.getAllPos(),
                jobLevelBiz.getAllJobLevels());
        if (employeeBiz.importEmployeeFile(emps)){
            return ResultFactory.buildSuccessResult("ok");
        } else {
            return ResultFactory.buildFailResult("导入失败");
        }
    }
}
