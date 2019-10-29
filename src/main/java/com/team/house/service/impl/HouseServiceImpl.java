package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.ExtHouse;
import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.utils.PageParam;
import com.team.house.utils.SearchTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<ExtHouse> getUserHouse(Integer id,  Integer pageNum,Integer pageSize,Integer state) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExtHouse> extHouseList = houseMapper.selectUserHouse(id,state);

        return new PageInfo<ExtHouse>(extHouseList);
    }

    @Override
    public int delHouse(Integer id,Integer state) {
        House house=new House();
        house.setId(id);
        house.setIsdel(state);
        return  houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int housePassState(Integer id, Integer state) {
        House house=new House();
        house.setId(id);
        house.setIspass(state);
        return  houseMapper.updateByPrimaryKeySelective(house);

    }

    @Override
    public PageInfo<ExtHouse> getAllHouse(Integer state,PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        List<ExtHouse> list = houseMapper.selectAllHouse(state);
        return new PageInfo<ExtHouse>(list);
    }

    @Override
    public PageInfo<ExtHouse> getHouseByTerm(SearchTerm searchTerm) {
        PageHelper.startPage(searchTerm.getPage(),searchTerm.getRows());
        List<ExtHouse> extHouseList = houseMapper.selectHouseByTerm(searchTerm);
        return new PageInfo<ExtHouse>(extHouseList);
    }

    @Override
    public ExtHouse getHouseById(Integer id) {
        return houseMapper.selectHouseById(id);
    }

    @Override
    public int updateHouseInfo(House house) {
        return  houseMapper.updateByPrimaryKeySelective(house);

    }
}
