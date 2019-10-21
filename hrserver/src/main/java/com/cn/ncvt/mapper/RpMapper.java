package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Rp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RpMapper {
    int deleteByID(Integer id);

    int insert(Rp rp);
    
    Rp selectByID(Integer id);

    int updateByIDSelective(Rp rp);

    int updateByID(Rp rp);
}