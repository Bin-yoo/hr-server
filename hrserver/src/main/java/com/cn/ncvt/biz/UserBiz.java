package com.cn.ncvt.biz;

import com.cn.ncvt.entity.User;
import com.cn.ncvt.mapper.UserMapper;
import com.cn.ncvt.mapper.UserRoleMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.cn.ncvt.util.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    UserRoleMapper userRoleMapper;

    public Result userLogin(User user) {
        User isEnabled =  userMapper.selectByUserName(user.getUsername());
        if (!isEnabled.isEnabled()){
            return ResultFactory.buildFailResult("该账号已被禁用,请联系管理员");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            Session session = subject.getSession();
            //将部分用户信息返回到前台
            User userDB =  userMapper.selectByUserNameLimit(user.getUsername());
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

    public Result getAllUserList(Integer page, Integer limit, String name) {
        PageHelper.startPage(page, limit);
        List<User> list = userMapper.selectUserByCondition(name);

        if (list != null){
            PageInfo pageInfo = new PageInfo(list);
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", list);
            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result addNewUser(User user) {
        Map<String,String> map = this.encodedPassword(user.getPassword());

        user.setSalt(map.get("salt"));
        user.setPassword(map.get("encodedPassword"));

        try {
            userMapper.insertFun(user);
            userRoleMapper.addUserRole(user.getId(),user.getRolesKey());
            return ResultFactory.buildSuccessResult("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败");
        }
    }

    public Result updateUser(User user) {
        try {
            userMapper.updateByID(user);
            userRoleMapper.deleteByUID(user.getId());
            userRoleMapper.addUserRole(user.getId(),user.getRolesKey());
            return ResultFactory.buildSuccessResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result deleteUser(Integer id) {
        try {
            userRoleMapper.deleteByUID(id);
            userMapper.deleteFun(id);
            return ResultFactory.buildSuccessResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public Result changeUserEnabledState(User user) {
        try {
            userMapper.updateByEnabled(user);
            return ResultFactory.buildSuccessResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result updatePassword(User user) {
        User checkpsw = userMapper.selectByID(user.getId());
        String password = user.getOldPassword();    //用户传进来的旧密码
        String salt = checkpsw.getSalt();    //数据库里的加密盐
        String encodedPassword = new SimpleHash("md5",password,salt,2).toString();    //使用md5算法,加密两次,生成加密后的密码
        if (!checkpsw.getPassword().equals(encodedPassword)){    //加密后与数据库内密文进行对比
            return ResultFactory.buildFailResult("旧密码错误!!!");
        }

        Map<String,String> map = this.encodedPassword(user.getPassword());
        user.setSalt(map.get("salt"));
        user.setPassword(map.get("encodedPassword"));

        try {
            userMapper.updatePasswordByID(user);
            return ResultFactory.buildSuccessResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    private Map<String,String> encodedPassword(String password){
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = new SimpleHash("md5",password,salt,2).toString();    //使用md5算法,加密两次,生成加密后的密码
        Map<String,String> map = new HashMap<>();
        map.put("salt", salt);
        map.put("encodedPassword", encodedPassword);
        return map;
    }
}
