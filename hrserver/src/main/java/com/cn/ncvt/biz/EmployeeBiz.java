package com.cn.ncvt.biz;

import com.cn.ncvt.entity.*;
import com.cn.ncvt.mapper.*;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version : V1.0
 * @ClassName: EmployeeBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:47
 **/
@Service
public class EmployeeBiz {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    PositionMapper positionMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    JobLevelMapper jobLevelMapper;
    @Autowired
    PoliticalStatusMapper politicalStatusMapper;
    @Autowired
    NationMapper nationMapper;

    public Result getAllEmployeeFile(Integer page, Integer limit,Integer departmentId,Integer positionId, Integer jobLevelId,String name) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Employee> fileList = employeeMapper.selectAllEmployee(departmentId,positionId,jobLevelId,name);

        if (fileList != null){
            //用PageInfo对结果进行包装,获取分页信息
            PageInfo pageInfo = new PageInfo(fileList);
            //包装map返回前端
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", fileList);

            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }


    public Result addEmployeeFile(Employee employee) {
        String password = employee.getIdCard().substring(employee.getIdCard().length() - 6);
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();    //生成加密盐
        int times = 2;    //加密两次
        String algorithmName = "md5";     //使用md5算法
        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();    //生成加密后的密码
        try{
            //employeeMapper.insertFun(employee);
            employeeMapper.insert(employee, encodedPassword, salt);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败，请检查您输入的资料");
        }
    }

    public Result updateEmployeeFile(Employee employee) {
        try{
            employeeMapper.updateFun(employee);
            return ResultFactory.buildSuccessResult("编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("编辑失败，请检查您输入的资料");
        }
    }

    public Result deleteByIdEmployeeFile(Integer id) {
        try{
            employeeMapper.deleteByIdFun(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public Result selectByIdEmployeeFile(Integer id) {
        Employee employee =employeeMapper.selectByIdFun(id);
        System.out.println(employee.getBirthday());
        if (employee != null){
            return ResultFactory.buildSuccessResult(employee);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result getAllDownMenu() {
        List<Department> departmentList=departmentMapper.selectByPID(-1);
        List<Position> positionList=positionMapper.selectPositionByCondition(null);
        List<JobLevel> jobLevelList=jobLevelMapper.selectJobLvlByCondition(null);
        List<Nation> nationList=nationMapper.selectAllNation();
        List<PoliticalStatus> politicalStatusList=politicalStatusMapper.selectAllPoliticalStatus();

        if (departmentList != null || positionList != null || jobLevelList != null || politicalStatusList != null){
            //包装map返回前端
            Map map = new HashMap();
            map.put("departmentList", departmentList);
            map.put("positionList", positionList);
            map.put("jobLevelList", jobLevelList);
            map.put("nationList", nationList);
            map.put("politicalStatusList", politicalStatusList);

            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result getMyFile() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Employee employee = employeeMapper.selectByIdFun(user.getEid());
        if (employee != null){
            return ResultFactory.buildSuccessResult(employee);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result checkWorkID(Integer workId) {
        Employee employee = employeeMapper.selectByWorkIdFun(workId);
        if (employee != null){
            return ResultFactory.buildFailResult("该工号已存在");
        } else {
            return ResultFactory.buildSuccessResult("工号未重复");
        }
    }

    public List<Employee> outputAllEmpFile() {
        List<Employee> list = employeeMapper.selectAllEmployeeFile();
        return list;
    }
}
