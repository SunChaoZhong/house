package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import com.kgc.house.util.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersServiceController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("getAllUsers")
    @ResponseBody
    public Map<String,Object> getAllUsers(UsersCondition usersCondition){
        PageInfo<Users> pageInfo = usersService.getAllUsers(usersCondition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addAllUsers")
    @ResponseBody
    public String addAllUsers(Users users){
        int i = usersService.addUsers(users);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("upUsers")
    @ResponseBody
    public String upUsers(Users users){
        int i = usersService.upUsers(users);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteUsers")
    @ResponseBody
    public String deleteUsers(Integer id){
        int i = usersService.deleteUsers(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("deleteMoreUsers")
    @ResponseBody
    //前台一般传递字符串，传不了数组
    public String deleteMoreUsers(String ids){//ids=1,2,3
        //将字符串转化为整型数组
        String [] arys=ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        int i = usersService.deleteMoreUsers(id);
        //返回json字符串
        return "{\"result\":"+i+"}";
    }
}
