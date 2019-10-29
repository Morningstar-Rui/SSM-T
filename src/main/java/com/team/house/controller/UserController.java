package com.team.house.controller;

import com.github.pagehelper.PageInfo;

import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.utils.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("getUserByCondition")
    @ResponseBody
    public Map<String,Object> getUserByCondition(UserParam userParam){
        PageInfo<Users> pageInfo = userService.getUserByCondition(userParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @RequestMapping("delUsersById")
    @ResponseBody
    public Map<String,Object> delUsersById(Integer id){
        int i = userService.delUsersById(id);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
}
