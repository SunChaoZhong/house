package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetServiceController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("getAllSteet")
    @ResponseBody
    public Map<String,Object> getAllSteet(Integer page, Integer rows, Integer did){
        PageInfo<Street> allStreet = streetService.getAllStreet(page, rows, did);
        Map<String,Object> map=new HashMap<>();
        map.put("total",allStreet.getTotal());
        map.put("rows",allStreet.getList());
        return map;
    }
}
