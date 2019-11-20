package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsMapper {

    HashMap<String,Long> selectAllEmpFile();

    List<Statistics> selectDepMemberNum();

    List<Statistics> selectPosMemberNum();

    List<Statistics> selectJobLvlMemberNum();

    List<Statistics> selectRp();

}
