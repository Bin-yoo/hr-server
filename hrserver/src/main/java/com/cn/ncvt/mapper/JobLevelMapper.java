package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.JobLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobLevelMapper {
    int deleteByID(Integer id);

    int insert(JobLevel jobLevel);

    JobLevel selectByID(Integer id);

    int updateByIDSelective(JobLevel jobLevel);

    List<JobLevel> selectJobLvlByCondition(@Param("name") String name);

    List<JobLevel> selectAllJobLevel();
}