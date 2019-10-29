package com.team.house.mapper;

import com.team.house.entity.ExtHouse;
import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.utils.SearchTerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<ExtHouse> selectUserHouse(@Param("id") Integer id,@Param("state") Integer state);

    ExtHouse selectHouseById(Integer id);

    List<ExtHouse> selectAllHouse(Integer state);

    List<ExtHouse> selectHouseByTerm(SearchTerm searchTerm);
}