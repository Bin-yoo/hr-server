package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: EmployeeMapper
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:53
 **/
@Mapper
public interface EmployeeMapper {
    
    void insertFun(Employee employee);

    void updateFun(Employee employee);

    void deleteByIdFun(Integer id);

    List<Employee> selectByIdFun(Integer id);

    List<Employee> selectAllEmployee(int departmentId, int positionId, int jobLevelId, String name);
}
