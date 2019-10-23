package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.EmployeeSalary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeSalaryMapper {
    int deleteByID(Integer id);

    int insert(EmployeeSalary employeeSalary);

    EmployeeSalary selectByID(Integer id);

    int updateByIDSelective(EmployeeSalary employeeSalary);

    int updateByID(EmployeeSalary employeeSalary);
}