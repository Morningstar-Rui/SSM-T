package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.utils.PageParam;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistrict();

    //分页显示
    PageInfo<District> getDistrictByPage(PageParam pageParam);

    //添加区域
    int addDistrict(District district);

    //根据id查询对应的区域
    District getDistrictById(Integer id);

    //修改区域
    int updateDistrict(District district);

    //根据id删除单条
    int delDistrict(Integer id);

    //根据id删除多条记录
    int delDistrictByIds(List<Integer> values);
}
