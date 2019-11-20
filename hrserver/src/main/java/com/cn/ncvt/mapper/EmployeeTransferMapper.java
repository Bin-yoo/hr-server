package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.EmployeeTransfer;

public interface EmployeeTransferMapper {
    int deleteByID(Integer id);

    int insert(EmployeeTransfer record);

    int insertSelective(EmployeeTransfer record);

    EmployeeTransfer selectByID(Integer id);

    int updateByIDSelective(EmployeeTransfer record);

    int updateByID(EmployeeTransfer record);
}