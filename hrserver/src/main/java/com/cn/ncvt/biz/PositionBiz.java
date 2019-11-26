package com.cn.ncvt.biz;

import com.cn.ncvt.entity.Position;
import com.cn.ncvt.mapper.PositionMapper;
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
 * @ClassName: PositionBiz
 * @Description: TODO
 * @Auther: Bin
 * @Date: 2019/10/24 20:17
 **/
@Service
public class PositionBiz {

    @Autowired
    PositionMapper positionMapper;

    public Result getAllPosition(Integer page, Integer limit, String name) {
        PageHelper.startPage(page, limit);
        List<Position> positionList = positionMapper.selectPositionByCondition(name);

        if (positionList != null){
            PageInfo pageInfo = new PageInfo(positionList);
            Map map = new HashMap();
            map.put("total", pageInfo.getTotal());
            map.put("totalPage" , pageInfo.getPages());
            map.put("page" , pageInfo.getPageNum());
            map.put("list", positionList);
            return ResultFactory.buildSuccessResult(map);
        } else {
            return ResultFactory.buildFailResult("获取失败");
        }
    }

    public Result addNewPosition(Position position) {
        try{
            positionMapper.insert(position);
            return ResultFactory.buildSuccessResult("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("添加失败，请检查您输入的资料");
        }
    }

    public Result updatePosition(Position position) {
        try{
            positionMapper.updateByIDSelective(position);
            return ResultFactory.buildSuccessResult("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("修改失败，请检查您输入的资料");
        }
    }

    public Result deletePosition(Integer id) {
        try{
            positionMapper.deleteByID(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("删除失败");
        }
    }

    public List<Position> getAllPos() {
        return positionMapper.selectAllPosi();
    }
}
