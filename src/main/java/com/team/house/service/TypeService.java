package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.utils.PageParam;

import java.util.List;

public interface TypeService {
    List<Type> getAllType();

    //分页显示
    PageInfo<Type> getTypeByPage(PageParam pageParam);

    //添加区域
    int addType(Type district);

    //根据id查询对应的区域
    Type getTypeById(Integer id);

    //修改区域
    int updateType(Type district);

    //根据id删除单条
    int delType(Integer id);

    //根据id删除多条记录
    int delTypeByIds(List<Integer> values);
}
