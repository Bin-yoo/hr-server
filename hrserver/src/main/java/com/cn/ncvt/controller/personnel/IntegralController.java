package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.IntegralBiz;
import com.cn.ncvt.biz.RpBiz;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: IntegralController
 * @Auther: Ashe
 * @Date: 2019/10/24
 **/
@RestController
@RequestMapping("/integral")
@Api(tags = "员工积分")
public class IntegralController {
    @Autowired
    IntegralBiz integralBiz;
    @Autowired
    RpBiz rpBiz;

    @GetMapping("/allIntegral")
    @ApiOperation(value = "查看所有员工的积分", notes = "")
    public Result allIntegralFile(int page, int limit,Integer departmentId,Integer positionId,String name){
        return integralBiz.getAllIntegralFile(page, limit,departmentId,positionId,name);
    }


}
