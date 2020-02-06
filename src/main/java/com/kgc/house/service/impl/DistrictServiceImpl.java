package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<District> getAllDistrict(Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page,pageSize);
        //条件实体
        DistrictExample example=new DistrictExample();
        //DistrictExample.Criteria criteria = example.createCriteria();
        List<District> districts = districtMapper.selectByExample(example);
        return new PageInfo<District>(districts);
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public int upDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Transactional
    @Override
    public int deleteDistrict(Integer id) {
        try{
            streetMapper.deleteStreetByDid(id);
            //考虑业务  删除区域相应的街道也到删除，事物管理
            districtMapper.deleteByPrimaryKey(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int deleteMoreDistreet(Integer[] ids) {
        return districtMapper.deleteMoreDistreet(ids);
    }

    @Override
    public List<District> allDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }


}
