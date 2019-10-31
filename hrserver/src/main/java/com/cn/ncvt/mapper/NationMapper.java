package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Nation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationMapper {
    int deleteByID(Integer id);

    int insert(Nation nation);

    Nation selectByID(Integer id);

    int updateByIDSelective(Nation nation);

    int updateByID(Nation nation);

    List<Nation> selectAllNation();
}