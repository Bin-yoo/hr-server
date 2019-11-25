package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int addRolePermission(@Param("rid") int rid, @Param("checkedKeys") int[] checkedKeys);

    int deleteByRid(Integer rid);
}