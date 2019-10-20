package com.cn.ncvt.biz;

import com.cn.ncvt.entity.User;
import com.cn.ncvt.entity.Menu;
import com.cn.ncvt.mapper.MenuMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: MenuBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/12 14:42
 **/

@Service
public class MenuBiz {

    @Autowired
    MenuMapper menuMapper;

    public Result getMenusByUserId() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return ResultFactory.buildSuccessResult(menuMapper.selectMenusByUserId(user.getId()));
    }

    public Result getMenuTreeByRoleId(int rid) {

        List<Menu> menus = menuMapper.getMenuTreeByRid(rid);
        if (menus != null){
            return ResultFactory.buildSuccessResult(menus);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }
}
