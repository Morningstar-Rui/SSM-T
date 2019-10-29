package com.team.house.service.impl;

import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public List<Street> getAllStreetByDistrictId(Integer districtId) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
        return streetMapper.selectByExample(example) ;
    }
}
