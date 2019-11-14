package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    void updateByEnabled(User user);

    List<User> selectUserByCondition(@Param("name") String name);

    void updatePasswordByID(User user);
}
