package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Role;
import com.cn.ncvt.mapper.MenuRoleMapper;
import com.cn.ncvt.mapper.RoleMapper;
import com.cn.ncvt.mapper.RolePermissionMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version : V1.0
 * @ClassName: RoleBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/14 15:58
 **/
@Service
public class RoleBiz {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public Result roles(Integer page, Integer limit, String name) {
        PageHelper.startPage(page, limit);
        //List<Role> roleList = roleMapper.selectAllRoles();
        List<Role> roleList = roleMapper.selectRolesByCondition(name);

        if (roleList != null){
            PageInfo pageInfo = new PageInfo(roleList);
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", roleList);
            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result addNewRole(Role role) {
        try {
            roleMapper.insertFun(role);
            return ResultFactory.buildSuccessResult("添加成功");
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败");
        }
    }

    public Result deleteRoleByRid(int rid) {
        try {
            rolePermissionMapper.deleteByRid(rid);
            menuRoleMapper.deleteByRid(rid);    //数据库表不设置级联,所以要先删除关联的菜单权限表中数据
            roleMapper.deleteRoleById(rid);
            return ResultFactory.buildSuccessResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public Result updateRole(Role role) {
        try {
            roleMapper.updateFun(role);
            return ResultFactory.buildSuccessResult("更新成功");
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("更新失败");
        }
    }
}
