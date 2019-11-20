package com.cn.ncvt.biz;

import com.cn.ncvt.entity.JobLevel;
import com.cn.ncvt.mapper.JobLevelMapper;
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
 * @ClassName: JobLevelBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/24 10:44
 **/
@Service
public class JobLevelBiz {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public Result getAllJobLvl(Integer page, Integer limit, String name) {
        PageHelper.startPage(page, limit);
        List<JobLevel> jobLevelList = jobLevelMapper.selectJobLvlByCondition(name);

        if (jobLevelList != null){
            PageInfo pageInfo = new PageInfo(jobLevelList);
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", jobLevelList);
            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result addNewJobLvl(JobLevel jobLevel) {
        try{
            jobLevelMapper.insert(jobLevel);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败，请检查您输入的资料");
        }
    }

    public Result updateJobLvl(JobLevel jobLevel) {
        try{
            jobLevelMapper.updateByIDSelective(jobLevel);
            return ResultFactory.buildSuccessResult("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败，请检查您输入的资料");
        }
    }

    public Result deleteJobLvl(Integer id) {
        try{
            jobLevelMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }
}
