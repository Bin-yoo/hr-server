package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Employee;
import com.cn.ncvt.mapper.EmployeeMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version : V1.0
 * @ClassName: EmployeeBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/21 16:47
 **/
@Service
public class EmployeeBiz {

    @Autowired
    EmployeeMapper employeeMapper;

    public Result getAllEmployeeFile(int page, int limit) {
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, limit);
        List<Employee> fileList = employeeMapper.selectAllEmployee();

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
