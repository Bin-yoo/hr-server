package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Employee;
import com.cn.ncvt.entity.EmployeeSalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeSalaryMapper {
    int deleteByID(Integer id);

    int insert(EmployeeSalary employeeSalary);

    EmployeeSalary selectByID(Integer id);

    int updateByIDSelective(@Param("empSalary") EmployeeSalary employeeSalary);

    List<EmployeeSalary> selectAllEmployeeSalary(@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId, @Param("jobLevelId")  Integer jobLevelId, @Param("name")String name);
}