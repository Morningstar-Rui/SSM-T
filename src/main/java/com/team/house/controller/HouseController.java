package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.ExtHouse;
import com.team.house.service.HouseService;
import com.team.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("houseIsPass")
    @ResponseBody
    public Map<String,Object> houseIsPass(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
        int i = houseService.housePassState(id, 1);//通过审核
        map.put("result",i);
        return map;
    }
    @RequestMapping("houseNotPass")
    @ResponseBody
    public Map<String,Object> houseNotPass(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
        int i = houseService.housePassState(id, 0);//取消审核
        map.put("result",i);
        return map;
    }

    @RequestMapping("getAllHouseNotPass")
    @ResponseBody
    public Map<String,Object> getAllHouseNotPass(PageParam pageParam){
        Map<String,Object> map=new HashMap<String, Object>();
        PageInfo<ExtHouse> allHouse = houseService.getAllHouse(0,pageParam);//查询未审核的房子
        map.put("rows",allHouse.getList());
        map.put("total",allHouse.getTotal());
        return map;
    }
    @RequestMapping("getAllHouseIsPass")
    @ResponseBody
    public Map<String,Object> getAllHouseIsPass(PageParam pageParam){
        Map<String,Object> map=new HashMap<String, Object>();
        PageInfo<ExtHouse> allHouse = houseService.getAllHouse(1,pageParam);//查询未审核的房子
        map.put("rows",allHouse.getList());
        map.put("total",allHouse.getTotal());
        return map;
    }

}
