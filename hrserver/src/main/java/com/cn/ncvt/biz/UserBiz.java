package com.cn.ncvt.biz;

import com.cn.ncvt.entity.User;
import com.cn.ncvt.mapper.UserMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.cn.ncvt.util.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @version : V1.0
 * @ClassName: UserBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/9/24 16:58
 **/

@Service
public class UserBiz {

    @Autowired
    UserMapper userMapper;


    public Result userLogin(User user) {

        if (user.getUsername().equals("") || user.getUsername() == null || user.getPassword().equals("") || user.getPassword() == null){
            return ResultFactory.buildPermissionFailResult("登录失败,用户名或密码错误");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            Session session = subject.getSession();
            //将部分用户信息返回到前台
            User userDB =  this.getByName(user.getUsername());
            //创建jwt token实例,准备派发token
            Token userToken = new Token();
            Map map = new HashMap();
            map.put("user",userDB);
            map.put("sessionId",session.getId());
            map.put("token",userToken.createTokenWithClaim(userDB.getId(), userDB.getUsername()));
            return ResultFactory.buildSuccessResult(map);
        } catch (AuthenticationException e){
            e.printStackTrace();
            return ResultFactory.buildPermissionFailResult("登录失败,用户名或密码错误");
        }
    }

    public User getByName(String userName){
        return userMapper.selectByUserName(userName);
    }
}