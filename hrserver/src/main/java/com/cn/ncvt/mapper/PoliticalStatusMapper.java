package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.PoliticalStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PoliticalStatusMapper {
    int deleteByID(Integer id);

    int insert(PoliticalStatus politicalStatus);

    PoliticalStatus selectByID(Integer id);

    int updateByIDSelective(PoliticalStatus politicalStatus);

    int updateByID(PoliticalStatus politicalStatus);
}