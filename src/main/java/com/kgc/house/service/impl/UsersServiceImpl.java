package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.UsersService;
import com.kgc.house.util.MD5Utils;
import com.kgc.house.util.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getAllUsers(UsersCondition usersCondition) {
        //开启分页
        PageHelper.startPage(usersCondition.getPage(),usersCondition.getRows());
        //条件实体
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(new Integer(1));//管理员
        //添加查询条件
        if (usersCondition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+usersCondition.getTelephone()+"%");
        }
        //大于开始年龄
        if (usersCondition.getStartAge()!=null){
            criteria.andAgeGreaterThan(usersCondition.getStartAge());
        }
        //小于结束年龄
        if (usersCondition.getEndAge()!=null){
            criteria.andAgeLessThan(usersCondition.getEndAge());
        }
        List<Users> users = usersMapper.selectByExample(example);
        return new PageInfo<Users>(users);
    }

    @Override
    public int addUsers(Users users) {
        users.setIsadmin(0);
        //加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    @Override
    public int upUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public int deleteUsers(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreUsers(Integer[] ids) {
        return usersMapper.deleteMoreUsers(ids);
    }

    @Override
    public int checkUname(String username) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);//注册用户
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size()==0?0:1;
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(0);//注册用户
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size()==1){
            return users.get(0);
        }
        return null;

    }
}
