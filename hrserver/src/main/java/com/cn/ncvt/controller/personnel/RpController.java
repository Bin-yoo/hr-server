package com.cn.ncvt.controller.personnel;

import com.cn.ncvt.biz.RpBiz;
import com.cn.ncvt.entity.Rp;
import com.cn.ncvt.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RpController
 * @Auther: Ashe
 * @Date: 2019/10/31
 **/
@RestController
@RequestMapping("/rp")
@Api(tags = "奖惩管理")
public class RpController {
    @Autowired
    RpBiz rpBiz;

    @GetMapping("/allRp")
    @ApiOperation(value = "查看所有员工的奖惩记录", notes = "")
    public Result allRpFile(Integer page, Integer limit,Integer departmentId,Integer positionId, String name){
        return rpBiz.getAllRpFile(page, limit, departmentId, positionId, name);
    }

    @PostMapping("/addRp")
    @ApiOperation(value = "添加奖惩记录", notes = "请将没必要填的东西设置为空")
    public Result addEmployeeFile(@RequestBody Rp Rp){
        return rpBiz.addRpFile(Rp);
    }

    @PutMapping("/updateRp")
    @ApiOperation(value = "奖惩修改记录", notes = "")
    public Result updateRpFile(@RequestBody Rp rp){
        return rpBiz.updateRpFile(rp);
    }

    @DeleteMapping("/deleteRp/{id}")
    @ApiOperation(value = "删除奖惩记录", notes = "")
    public Result deleteByIdEmployeeFile(@PathVariable Integer id){
        return rpBiz.deleteRPFile(id);
    }

    @GetMapping("/empRp/{eid}")
    @ApiOperation(value = "查看某个员工所有的奖惩记录", notes = "")
    public Result empAllRpFile(@PathVariable Integer eid,int page, int limit){
        return rpBiz.empAllRpFile(eid,page,limit);
    }
}
