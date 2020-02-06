package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.util.UsersCondition;

public interface UsersService {
    //分页
    PageInfo<Users> getAllUsers(UsersCondition usersCondition);
    //添加，注册
    public int addUsers(Users users);

    public int upUsers(Users users);
    //删除一条
    public int deleteUsers(Integer id);
    //删除选中的
    int deleteMoreUsers(Integer[] ids);
    //检查用户名是否存在
    int checkUname(String username);
    //登入功能
    Users login(String username,String password);

}
