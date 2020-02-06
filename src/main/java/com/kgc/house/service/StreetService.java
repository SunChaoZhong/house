package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;

import java.util.List;

public interface StreetService {
    PageInfo<Street> getAllStreet(Integer page,Integer rows,Integer did);
    //区域id获取街道
    List<Street> getStreetByDid(Integer did);
}
