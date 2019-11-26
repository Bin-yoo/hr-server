package com.cn.ncvt.biz;

import com.cn.ncvt.entity.PoliticalStatus;
import com.cn.ncvt.mapper.PoliticalStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: PoliticalStatusBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/26 16:01
 **/
@Service
public class PoliticalStatusBiz {

    @Autowired
    PoliticalStatusMapper politicalStatusMapper;

    public List<PoliticalStatus> getAllPolitics() {
        return politicalStatusMapper.selectAllPoliticalStatus();
    }
}
