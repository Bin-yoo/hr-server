package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Integral;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntegralMapper {
    int deleteByID(Integer id);

    int insert(Integral integral);

    Integral selectByID(Integer id);

    int updateByIDSelective(Integral integral);

    int updateByID(Integral integral);
}