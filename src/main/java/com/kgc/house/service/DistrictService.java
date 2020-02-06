package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;

import java.util.List;

public interface DistrictService {
    //分页
    PageInfo<District> getAllDistrict(Integer page,Integer pageSize);
    //添加区域
    public int addDistrict(District district);
    //修改区域
    public int upDistrict(District district);
    //删除一条
    public int deleteDistrict(Integer id);
    //删除选中的区域
    int deleteMoreDistreet(Integer[] ids);
    //查询所有区域
    List<District> allDistrict();
}
