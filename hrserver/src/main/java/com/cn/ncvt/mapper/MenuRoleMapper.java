package com.cn.ncvt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuRoleMapper {
    void deleteByRid(int rid);

    void addMenuRole(@Param("rid") int rid, @Param("checkedKeys") int[] checkedKeys);

}
