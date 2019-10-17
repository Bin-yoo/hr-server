package com.cn.ncvt.mapper;

import com.cn.ncvt.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectMenusByHrId(int id);

    List<Menu> getMenuTreeByRid(int rid);

}
