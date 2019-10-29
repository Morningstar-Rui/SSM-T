package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.utils.MD5Utils;
import com.team.house.utils.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    public PageInfo<Users> getUserByCondition(UserParam userParam) {
        PageHelper.startPage(userParam.getPage(),userParam.getRows());
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (userParam.getName()!=null){
            criteria.andNameLike("%"+userParam.getName()+"%");
        }
        if (userParam.getTelephone()!=null){
            criteria.andTelephoneLike("%"+userParam.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(example);
        return new PageInfo<Users>(list);
    }

    public int delUsersById(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    public int checkName(String name) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (name!=null&&!name.isEmpty()){
            criteria.andNameEqualTo(name);
        }
        List<Users> list = usersMapper.selectByExample(example);
        if (list.size()!=0){
            return list.size();
        }
        return 0;
    }

    public int regUser(Users users) {
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        int i = usersMapper.insertSelective(users);
        return i;
    }

    public Users loginByTerm(String name, String password) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> usersList = usersMapper.selectByExample(example);
        if (usersList.size()!=0){
            return usersList.get(0);
        }
        return null;
    }
}
