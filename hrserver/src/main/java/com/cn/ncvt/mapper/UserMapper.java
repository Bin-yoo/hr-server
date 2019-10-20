package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUserName(String userName);
}
