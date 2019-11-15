package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Assessment;
import com.cn.ncvt.mapper.AssessmentMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.RespectBinding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AssessmentBiz
 * @Auther: Ashe
 * @Date: 2019/10/23
 **/
@Service
public class AssessmentBiz {

    @Autowired
    AssessmentMapper assessmentMapper;

    public Result getAllAssessmentFile(Integer page, Integer limit, String state, String name, Integer did) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Assessment> fileList = assessmentMapper.selectAllAssessment(state,name,did);

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

    public Result addAssessmentFile(Assessment assessment) {
        try {
            assessmentMapper.insert(assessment);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败");
        }
    }

    public Result updateAssessmentFile(Assessment assessment) {
        try {
            assessmentMapper.updateByID(assessment);
            return ResultFactory.buildSuccessResult("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result updateAssessmentState(Assessment assessment) {
        try {
            assessmentMapper.updateAssessmentStateByID(assessment);
            return ResultFactory.buildSuccessResult("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result deleteAssessmentFile(Integer id) {
        try{
            assessmentMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public Result getMyAssessmentFile(Integer did, Integer page, Integer limit, String state, String name) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Assessment> fileList = assessmentMapper.selectMyAssessmentByDid(did,state,name);

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
