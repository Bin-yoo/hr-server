package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.SalaryLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SalaryLogMapper {
    List<SalaryLog> selectByEid();
}