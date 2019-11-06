package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Role;
import com.cn.ncvt.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByID(Integer id);

    int deleteByUID(Integer id);

    int insert(UserRole userRole);

    UserRole selectByID(Integer id);

    int updateByIDSelective(UserRole userRole);

    int updateByID(UserRole userRole);

    void addUserRole(@Param("uid") Integer id, @Param("rolesKey") int[] rolesKey);
}