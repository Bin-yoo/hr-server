package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int deleteByID(Integer id);

    int insert(Department department);

    Department selectByID(Integer id);

    int updateByIDSelective(Department department);

    int updateByID(Department department);

    List<Department> selectAllDepartment();
}