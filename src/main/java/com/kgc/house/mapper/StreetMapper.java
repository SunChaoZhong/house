package com.kgc.house.mapper;

import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);
    //删除区域的同时删除对应的街道
    int deleteStreetByDid(Integer id);
}