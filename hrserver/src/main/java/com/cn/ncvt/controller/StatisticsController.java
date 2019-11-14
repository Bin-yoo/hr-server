package com.cn.ncvt.controller;

import com.cn.ncvt.biz.StatisticsBiz;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version : V1.0
 * @ClassName: StatisticsController
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/13 15:25
 **/
@RestController
@RequestMapping("/statistics")
@Api(tags = "综合统计")
public class StatisticsController {

    @Autowired
    StatisticsBiz statisticsBiz;

    @GetMapping("/totalfile")
    @ApiOperation(value = "加载统计数据")
    public Result getAllFile(){
        return statisticsBiz.getAllEmpFile();
    }

}
