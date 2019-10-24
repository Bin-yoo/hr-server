package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PositionMapper {
    int deleteByID(Integer id);

    int insert(Position position);

    Position selectByID(Integer id);

    int updateByIDSelective(Position position);

    List<Position> selectPositionByCondition(@Param("name") String name);

}