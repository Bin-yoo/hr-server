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
    List<Role> roles();

    int addNewRole(Role role);

    int deleteRoleById(int rid);
}
