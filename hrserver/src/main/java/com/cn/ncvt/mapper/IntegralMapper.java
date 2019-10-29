package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Integral;
import com.cn.ncvt.entity.Rp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntegralMapper {
    int deleteByID(Integer id);

    int insert(Integral integral);

    Integral selectByID(Integer id);

    int updateByIDSelective(Integral integral);

    int updateByID(Integral integral);

    List<Integral> selectAllIntegerFile();

    List<Rp> selectAlRpFile();
}