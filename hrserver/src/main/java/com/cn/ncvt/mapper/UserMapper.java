package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByUserName(String userName);

    User selectByUserNameLimit(String userName);

    List<User> selectAllFun();

    void deleteFun(Integer id);

    void insertFun(User user);

    User selectByID(Integer id);

    int updateByID(User record);
}
