package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeServiceController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getAllType")
    @ResponseBody
    public Map<String,Object> getAllType(Integer page, Integer rows){
        PageInfo<Type> pageInfo = typeService.getAllType(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addAllType")
    @ResponseBody
    public String addAllType(Type type){
        int i = typeService.addType(type);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("upType")
    @ResponseBody
    public String upType(Type type){
        int i = typeService.upType(type);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteType")
    @ResponseBody
    public String deleteType(Integer id){
        int i = typeService.deleteType(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteMoreType")
    @ResponseBody
    //前台一般传递字符串，传不了数组
    public String deleteMoreType(String ids){//ids=1,2,3
        //将字符串转化为整型数组
        String [] arys=ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        int i = typeService.deleteMoreType(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }
}
