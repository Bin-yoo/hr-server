package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.SalaryLog;

import java.util.List;

public interface SalaryLogMapper {
    List<SalaryLog> selectByEid();
}