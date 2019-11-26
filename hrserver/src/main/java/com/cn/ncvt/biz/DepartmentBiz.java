package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Department;
import com.cn.ncvt.mapper.DepartmentMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: DepartmentBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/31 15:36
 **/
@Service
public class DepartmentBiz {

    @Autowired
    DepartmentMapper departmentMapper;

    public Result getDepTree() {
        List<Department> list = departmentMapper.selectByPID(-1);
        if (list != null){
            return ResultFactory.buildSuccessResult(list);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result addDepartment(Department department) {
        if (department.getParentId() == null || department.getParentId() == 0){
            department.setParentId(-1);
        }
        Department checkDepId = departmentMapper.selectByDepNum(department.getDepNum());
        if (checkDepId != null)
            return ResultFactory.buildFailResult("部门编号已存在");

        try {
            departmentMapper.insert(department);
            return ResultFactory.buildSuccessResult("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败");
        }
    }

    public Result updateDepartment(Department department) {
        if (department.getParentId() == null || department.getParentId() == 0){
            department.setParentId(-1);
        } else {
            Department checkParentId = departmentMapper.selectByID(department.getParentId());
            if (checkParentId.getParentId() == department.getId()){
                return ResultFactory.buildFailResult("上级部门不能设置为你所编辑部门的子级部门");
            }
        }
        Department checkDepId = departmentMapper.selectByDepNum(department.getDepNum());
        if (checkDepId != null && checkDepId.getId() != department.getId())
            return ResultFactory.buildFailResult("部门编号已存在");

        try {
            departmentMapper.updateByIDSelective(department);
            return ResultFactory.buildSuccessResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result deleteDepartment(Integer id) {
        try {
            departmentMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public List<Department> getAllDeps() {
        return departmentMapper.selectAllDep();
    }
}
