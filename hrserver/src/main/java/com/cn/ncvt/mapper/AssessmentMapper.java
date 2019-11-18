package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Assessment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssessmentMapper {
    int deleteByID(Integer id);

    int insert(Assessment assessment);

    Assessment selectByID(Integer id);

    int updateByIDSelective(Assessment assessment);

    int updateByID(Assessment assessment);

    int updateAssessmentStateByID(Assessment assessment);

    List<Assessment> selectAllAssessment(@Param("state") String state, @Param("name") String name, @Param("did") Integer did);

    //List<Assessment> selectMyAssessmentByDid(@Param("did") Integer did,@Param("state") String state,@Param("name") String name);

    List<Assessment> selectMyAssessment(@Param("did") Integer did, @Param("eid") Integer eid, @Param("state") String state, @Param("name") String name);
}