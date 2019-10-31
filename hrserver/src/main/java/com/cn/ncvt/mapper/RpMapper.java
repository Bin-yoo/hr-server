package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Rp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RpMapper {
    int deleteByID(Integer id);

    int insert(Rp rp);

    List<Rp> selectByID(Integer id);

    int updateByIDSelective(Rp rp);

    int updateByID(Rp rp);

    List<Rp> selectAllRpByID(Integer eid);

    List<Rp> selectAllRp();
}