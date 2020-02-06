package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;

import java.util.List;

public interface TypeService {
    //分页
    PageInfo<Type> getAllType(Integer page, Integer pageSize);
    //添加区域
    public int addType(Type type);
    //修改区域
    public int upType(Type type);
    //删除一条
    public int deleteType(Integer id);
    //删除选中的区域
    int deleteMoreType(Integer[] ids);
    //查询所有类型
    List<Type> allType();
}
