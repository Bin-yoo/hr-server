package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Permission;
import com.cn.ncvt.mapper.PermissionMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @version : V1.0
 * @ClassName: PermissionBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/25 20:23
 **/

@Service
public class PermissionBiz {

    @Autowired
    PermissionMapper permissionMapper;

    public Result getPermissionTreeByRid(Integer rid) {

        List<Permission> permission = permissionMapper.selectPermissionByRid(rid);
        if (permission != null){
            return ResultFactory.buildSuccessResult(permission);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }

    }

    public boolean needInterceptor(String requestURI) {
        Permission p = permissionMapper.selectAll(requestURI);
        if (p!=null){
            return true;
        }
        return false;
    }

    public List<String> listPermissionURLs(Integer id) {
        return permissionMapper.selectByUID(id);
    }
}
