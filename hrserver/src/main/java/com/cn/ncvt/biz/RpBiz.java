package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Integral;
import com.cn.ncvt.entity.Rp;
import com.cn.ncvt.mapper.RpMapper;
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
 * @ClassName: RpBiz
 * @Auther: Ashe
 * @Date: 2019/10/24
 **/
@Service
public class RpBiz {
    @Autowired
    RpMapper rpMapper;


    public Result getRpFile(Integer eid, int page, int limit) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Rp> fileList = rpMapper.selectAllRpByID(eid);

        if (fileList != null){
            //用PageInfo对结果进行包装,获取分页信息
            PageInfo pageInfo = new PageInfo(fileList);

            //包装map返回前端
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", fileList);

            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }
}
