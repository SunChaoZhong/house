package com.kgc.house.proController;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("checkUname")
    @ResponseBody
    public String checkUname(String username){
        int i = usersService.checkUname(username);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("regUser")
    public String regUser(Users users){
        int i = usersService.addUsers(users);
        if (i==0){
            return "error";
        }else
            return "login";
    }
    //登入
    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
        Users user = usersService.login(username, password);
        if (user==null){
            model.addAttribute("info","账号或密码错误");
            return "login";  //继续登入
        }else{
            //登入人的信息很多页面需要用  存入session或cookie适合
            session.setAttribute("user",user);
            //30秒过期
            //session.setMaxInactiveInterval(30);
            return "redirect:selectAllHousesByUid";
        }
    }
}
