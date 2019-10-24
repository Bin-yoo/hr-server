package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 2018/1/1.
 */
@Mapper
public interface RoleMapper {
    List<Role> selectAllRoles();

    int insertFun(Role role);

    int deleteRoleById(int rid);

    void updateFun(Role role);

    List<Role> selectRolesByCondition(@Param("name") String name);
}
