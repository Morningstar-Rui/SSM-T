package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.utils.UserParam;

public interface UserService {
    //根据搜索栏条件查询
    PageInfo<Users> getUserByCondition(UserParam userParam);

    //根据id删除
    int delUsersById(Integer id);

    //验证用户名重复功能
    int checkName(String name);

    //注册功能
    int regUser(Users users);

    //登录功能
    Users loginByTerm(String name,String password);
}
