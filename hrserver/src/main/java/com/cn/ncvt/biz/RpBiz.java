package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Employee;
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


    public Result empAllRpFile(Integer eid, int page, int limit) {
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

    public Result getAllRpFile(Integer page, Integer limit,Integer departmentId,Integer positionId, String name) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Rp> fileList = rpMapper.selectAllRp(departmentId, positionId, name);

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

    public Result updateRpFile(Rp rp) {
        try{
            rpMapper.updateByIDSelective(rp);
            return ResultFactory.buildSuccessResult("编辑成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("编辑失败，请检查您输入的资料");
        }
    }

    public Result deleteRPFile(Integer id) {
        try{
            rpMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public Result addRpFile(Rp rp) {
        try{
            rpMapper.insert(rp);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败，请检查您输入的资料");
        }
    }
}
