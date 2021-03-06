package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Employee;
import com.cn.ncvt.entity.EmployeeAssessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeAssessmentMapper {
    int deleteByID(Integer id);

    int insert(EmployeeAssessment employeeAssessment);

    EmployeeAssessment selectByID(Integer id);

    int updateByIDSelective(EmployeeAssessment employeeAssessment);

    int updateByID(EmployeeAssessment employeeAssessment);

    List<EmployeeAssessment> selectAllAssessmentByEid(Integer aid);

    EmployeeAssessment selectByEIDAndAID(@Param("eid") Integer eid, @Param("aid") Integer aid);

    List<Employee> selectByAID(@Param("aid") Integer aid, @Param("name") String name);
}