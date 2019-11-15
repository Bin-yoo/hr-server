package com.cn.ncvt.biz;

import com.cn.ncvt.entity.EmployeeAssessment;
import com.cn.ncvt.mapper.EmployeeAssessmentMapper;
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
 * @ClassName: EmployeeAssessmentBiz
 * @Auther: Ashe
 * @Date: 2019/10/23
 **/
@Service
public class EmployeeAssessmentBiz {
    @Autowired
    EmployeeAssessmentMapper employeeAssessmentMapper;
    public Result getAllAssessmentFile(Integer aid, Integer page, Integer limit) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<EmployeeAssessment> fileList = employeeAssessmentMapper.selectAllAssessmentByEid(aid);

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


    public Result addEmployeeAssessmentFile(EmployeeAssessment employeeAssessment) {
        try {
            employeeAssessmentMapper.insert(employeeAssessment);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败");
        }
    }


    public Result updateEmployeeAssessmentFile(EmployeeAssessment employeeAssessment) {
        try {
            employeeAssessmentMapper.updateByID(employeeAssessment);
            return ResultFactory.buildSuccessResult("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败");
        }
    }

    public Result deleteEmployeeAssessmentFile(Integer id) {
        try{
            employeeAssessmentMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }
}
