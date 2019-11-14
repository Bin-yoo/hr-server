package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface StatisticsMapper {

    HashMap<String,Long> selectAllEmpFile();

    List<Statistics> selectDepMemberNum();

    List<Statistics> selectPosMemberNum();

    List<Statistics> selectJobLvlMemberNum();

}
