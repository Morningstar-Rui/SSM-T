package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    public List<District> getAllDistrict() {
        DistrictExample example=new DistrictExample();
        return districtMapper.selectByExample(example);
    }

    public PageInfo<District> getDistrictByPage(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        DistrictExample example=new DistrictExample();
        List<District> list = districtMapper.selectByExample(example);
        PageInfo<District> pageInfo=new PageInfo<District>(list);
        return pageInfo;
    }

    public int addDistrict(District district) {
        return   districtMapper.insertSelective(district);

    }

    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Transactional
    public int delDistrict(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        streetMapper.deleteByExample(example);
        districtMapper.deleteByPrimaryKey(id);
        return 1 ;
    }
    @Transactional
    public int delDistrictByIds(List<Integer> ids) {
        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        StreetExample example1=new StreetExample();
        StreetExample.Criteria criteria1= example1.createCriteria();
        criteria.andIdIn(ids);
        criteria1.andDistrictIdIn(ids);
        streetMapper.deleteByExample(example1);//先删除街道
        districtMapper.deleteByExample(example);//再删除区域

        return 1;
    }
}
