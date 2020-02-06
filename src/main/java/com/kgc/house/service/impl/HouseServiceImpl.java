package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        int i = houseMapper.insertSelective(house);
        return i;
    }

    @Override
    public PageInfo<House> selectAllHousesByUid(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        List<House> houses = houseMapper.selectAllHousesByUid(uid);
        return new PageInfo<>(houses);
    }

    @Override
    public House selectHouse(String id) {
        return houseMapper.selectHouse(id);
    }

    @Override
    public int upHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }
}
