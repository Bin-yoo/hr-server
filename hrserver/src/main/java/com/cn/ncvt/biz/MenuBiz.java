package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Hr;
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

    public Result getMenusByHrId() {
        Hr hr = (Hr) SecurityUtils.getSubject().getPrincipal();
        return ResultFactory.buildSuccessResult(menuMapper.selectMenusByHrId(hr.getId()));
    }
}
