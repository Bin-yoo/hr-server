package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Hr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HrMapper {
    Hr selectByUserName(String userName);
}
