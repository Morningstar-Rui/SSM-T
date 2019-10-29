package com.team.house.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.ExtHouse;
import com.team.house.entity.House;
import com.team.house.utils.PageParam;
import com.team.house.utils.SearchTerm;

public interface HouseService {
    int addHouse(House house);

    PageInfo<ExtHouse> getUserHouse(Integer id, Integer pageNum,Integer pageSize,Integer state);

    /**
     * 状态码控制逻辑删除
     * @param id 指定删除的记录id
     * @param state 逻辑删除的状态码 0未删除|1删除
     * @return 影响行数
     */
    int delHouse(Integer id,Integer state);

    /**
     * 状态码控制审核结果
     * @param id 指定审核的记录id
     * @param state 审核的状态码 0 未审核 | 1已审核
     * @return 影响行数
     */
    int housePassState(Integer id,Integer state);

    /**
     * 管理员查询所有用户发布的房子
     * @param state 审核的状态码 0未审核 | 1 审核通过
     * @return 房子的信息
     */
    public PageInfo<ExtHouse> getAllHouse(Integer state,PageParam pageParam);


    PageInfo<ExtHouse> getHouseByTerm(SearchTerm searchTerm);

    ExtHouse getHouseById(Integer id);

    int updateHouseInfo(House house);
}
