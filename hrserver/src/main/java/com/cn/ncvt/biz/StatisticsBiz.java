package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Statistics;
import com.cn.ncvt.mapper.StatisticsMapper;
import com.cn.ncvt.result.Result;
import com.cn.ncvt.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version : V1.0
 * @ClassName: StatisticsBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/13 17:20
 **/
@Service
public class StatisticsBiz {

    @Autowired
    StatisticsMapper statisticsMapper;

    public Result getAllEmpFile() {

        Map resultmap = new HashMap();

        Map<String,Long> basic = statisticsMapper.selectAllEmpFile();
        List<Statistics> depnumlist = statisticsMapper.selectDepMemberNum();
        List<Statistics> posnumlist = statisticsMapper.selectPosMemberNum();
        Long total = basic.get("total");
        //创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        for (Statistics s : posnumlist) {
            String format = numberFormat.format( (float) s.getValue() / (float) total * 100);
            s.setProportion(format+"%");
        }
        List<Statistics> joblvlnumlist = statisticsMapper.selectJobLvlMemberNum();
        for (Statistics s : joblvlnumlist) {
            String format = numberFormat.format( (float) s.getValue() / (float) total * 100);
            s.setProportion(format+"%");
        }
        List<Statistics> rp = statisticsMapper.selectRp();
        resultmap.put("basic",basic);
        resultmap.put("depnumlist",depnumlist);
        resultmap.put("posnumlist",posnumlist);
        resultmap.put("joblvlnumlist",joblvlnumlist);
        resultmap.put("rp",rp);
        return ResultFactory.buildSuccessResult(resultmap);
    }
}
