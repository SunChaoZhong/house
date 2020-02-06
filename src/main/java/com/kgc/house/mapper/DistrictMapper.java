package com.kgc.house.mapper;

import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //删除选中的区域
    int deleteMoreDistreet(Integer[] ids);
}