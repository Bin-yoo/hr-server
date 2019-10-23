package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobLevelMapper {
    int deleteByID(Integer id);

    int insert(JobLevel jobLevel);

    JobLevel selectByID(Integer id);

    int updateByIDSelective(JobLevel jobLevel);

    int updateByID(JobLevel jobLevel);
}