package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.getAllType();
    }

    @RequestMapping("getTypeByPage")
    @ResponseBody
    public Map<String,Object> getTypeByPage(PageParam pageParam){
        PageInfo<Type> pageInfo = typeService.getTypeByPage(pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }
    @RequestMapping("addType")
    @ResponseBody
    public Map<String,Object> addType(Type type){
        int i = typeService.addType(type);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("getTypeById")
    @ResponseBody
    public Type getTypeById(Integer id){
        return typeService.getTypeById(id);
    }

    @RequestMapping("updateType")
    @ResponseBody
    public Map<String,Object> updateDistrict(Type type){
        int i = typeService.updateType(type);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("delType")
    @ResponseBody
    public Map<String,Object> delType(Integer id){
        int i = typeService.delType(id);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
    @RequestMapping("delTypeByIds")
    @ResponseBody
    public Map<String,Object> delDistrictByIds(@RequestParam("ids[]")Integer[] ids){
        List<Integer> idsList = Arrays.asList(ids);
        int i = typeService.delTypeByIds(idsList);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
}
