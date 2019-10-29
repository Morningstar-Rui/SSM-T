package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getAllDistrict")
    @ResponseBody
    public List<District> getAllDistrict(){
        return districtService.getAllDistrict();
    }

    @RequestMapping("getDistrictByPage")
    @ResponseBody
    public Map<String,Object> getDistrictByPage(PageParam pageParam){
        PageInfo<District> pageInfo = districtService.getDistrictByPage(pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    @RequestMapping("addDistrict")
    @ResponseBody
    public Map<String,Object> addDistrict(District district){
        int i = districtService.addDistrict(district);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("getDistrictById")
    @ResponseBody
    public District getDistrictById(Integer id){
        return districtService.getDistrictById(id);
    }
    @RequestMapping("updateDistrict")
    @ResponseBody
    public Map<String,Object> updateDistrict(District district){
        int i = districtService.updateDistrict(district);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("delDistrict")
    @ResponseBody
    public Map<String,Object> delDistrict(Integer id){
        int i = districtService.delDistrict(id);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("delDistrictByIds")
    @ResponseBody
    public Map<String,Object> delDistrictByIds(@RequestParam("ids[]")Integer[] ids){
        List<Integer> idsList = Arrays.asList(ids);
        int i = districtService.delDistrictByIds(idsList);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
}
