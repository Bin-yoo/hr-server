package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.SalaryLog;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SalaryLogMapper {
    List<SalaryLog> selectByEid(Integer eid);
}