package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictServiceController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("getAllDistrict")
    @ResponseBody
    public Map<String,Object> getAllDistrict(Integer page, Integer rows){
        PageInfo<District> pageInfo = districtService.getAllDistrict(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addAllDistrict")
    @ResponseBody
    public String addAllDistrict(District district){
        int i = districtService.addDistrict(district);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("upDistrict")
    @ResponseBody
    public String upDistrict(District district){
        int i = districtService.upDistrict(district);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteDistrict")
    @ResponseBody
    public String deleteDistrict(Integer id){
        int i = districtService.deleteDistrict(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteMoreDistrict")
    @ResponseBody
    //前台一般传递字符串，传不了数组
    public String deleteMoreDistrict(String ids){//ids=1,2,3
        //将字符串转化为整型数组
        String [] arys=ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        int i = districtService.deleteMoreDistreet(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }
}
