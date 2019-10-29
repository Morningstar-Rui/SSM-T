package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.*;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    public List<Type> getAllType() {
        TypeExample example=new TypeExample();
        return typeMapper.selectByExample(example);
    }

    public PageInfo<Type> getTypeByPage(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        TypeExample example=new TypeExample();
        List<Type> list = typeMapper.selectByExample(example);
        PageInfo<Type> pageInfo=new PageInfo<Type>(list);
        return pageInfo;
    }

    public int addType(Type type) {
        return   typeMapper.insertSelective(type);

    }

    public Type getTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Transactional
    public int delType(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
        return 1 ;
    }
    @Transactional
    public int delTypeByIds(List<Integer> ids) {
        TypeExample example=new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        typeMapper.deleteByExample(example);//再删除区域
        return 1;
    }
}
