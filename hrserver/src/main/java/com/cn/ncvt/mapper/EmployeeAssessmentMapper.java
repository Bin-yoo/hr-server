package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.EmployeeAssessment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeAssessmentMapper {
    int deleteByID(Integer id);

    int insert(EmployeeAssessment employeeAssessment);

    EmployeeAssessment selectByID(Integer id);

    int updateByIDSelective(EmployeeAssessment employeeAssessment);

    int updateByID(EmployeeAssessment employeeAssessment);
}