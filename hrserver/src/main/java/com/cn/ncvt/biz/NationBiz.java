package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Nation;
import com.cn.ncvt.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: NationBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/11/26 16:02
 **/
@Service
public class NationBiz {

    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.selectAllNation();
    }
}
