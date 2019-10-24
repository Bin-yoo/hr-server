package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Assessment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessmentMapper {
    int deleteByID(Integer id);

    int insert(Assessment assessment);

    Assessment selectByID(Integer id);

    int updateByIDSelective(Assessment assessment);

    int updateByID(Assessment assessment);

    List<Assessment> selectAllAssessment();
}