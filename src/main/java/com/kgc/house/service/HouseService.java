package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;

public interface HouseService {
    //保存发布房子信息
    int addHouse(House house);
    //发布人查看发布的房子(分页)
    PageInfo<House> selectAllHousesByUid(Integer page,Integer rows,Integer uid);
    //查询单条
    House selectHouse(String id);
    //修改房子信息
    int upHouse(House house);

}
