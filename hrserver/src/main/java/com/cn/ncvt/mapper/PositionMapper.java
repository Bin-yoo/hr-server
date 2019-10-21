package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Position;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper {
    int deleteByID(Integer id);

    int insert(Position position);

    Position selectByID(Integer id);

    int updateByIDSelective(Position position);

    int updateByID(Position position);
}