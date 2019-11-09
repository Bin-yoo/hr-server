package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Integral;
import com.cn.ncvt.entity.Rp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IntegralMapper {
    int deleteByID(Integer id);

    int insert(Integral integral);

    Integral selectByID(Integer id);

    int updateByIDSelective(Integral integral);

    int updateByID(Integral integral);

    List<Integral> selectAllIntegerFile(@Param("departmentId") Integer departmentId,@Param("positionId") Integer positionId,@Param("name")  String name);

    List<Rp> selectAlRpFile();
}