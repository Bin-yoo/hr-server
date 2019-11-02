package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.salaryLog;

public interface salaryLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(salaryLog record);

    int insertSelective(salaryLog record);

    salaryLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(salaryLog record);

    int updateByPrimaryKey(salaryLog record);
}