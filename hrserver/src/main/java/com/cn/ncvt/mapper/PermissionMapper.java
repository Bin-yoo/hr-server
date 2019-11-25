package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissionByRid(Integer rid);

    Permission selectAll(String requestURI);

    List<String> selectByUID(Integer id);
}