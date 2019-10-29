package com.team.house.service;

import com.team.house.entity.Street;

import java.util.List;

public interface StreetService {
    //根据districtId获取街道信息
    List<Street> getAllStreetByDistrictId(Integer districtId);
}
