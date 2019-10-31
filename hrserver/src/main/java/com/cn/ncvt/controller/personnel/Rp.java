package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.RpBiz;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: Rp
 * @Auther: Ashe
 * @Date: 2019/10/31
 **/
@RestController
@RequestMapping("/rp")
@Api(tags = "奖惩管理")
public class Rp {
    @Autowired
    RpBiz rpBiz;

    @GetMapping("/allRp")
    @ApiOperation(value = "查看所有员工的奖惩记录", notes = "")
    public Result allRpFile(int page, int limit){
        return rpBiz.getAllRpFile(page, limit);
    }
}
