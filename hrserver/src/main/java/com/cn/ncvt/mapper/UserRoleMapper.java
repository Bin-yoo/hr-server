package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int deleteByID(Long id);

    int insert(UserRole userRole);

    UserRole selectByID(Long id);

    int updateByIDSelective(UserRole userRole);

    int updateByID(UserRole userRole);
}