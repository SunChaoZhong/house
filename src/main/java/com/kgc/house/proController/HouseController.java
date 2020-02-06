package com.kgc.house.proController;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.House;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.Users;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.StreetService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private StreetService streetService;

    @RequestMapping("goFabu")
    public String goFabu(Model model){
        List<Type> types = typeService.allType();
        List<District> districts = districtService.allDistrict();
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        return "fabu";
    }

    @RequestMapping("addHouse")
    public String addHouse(HttpSession session,House house,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        //上传图片
        String fname=pfile.getOriginalFilename();//得到文件名
        String saveName=System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."));
        File file=new File("D:\\images\\"+saveName);
        try {
            pfile.transferTo(file);//保存
        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存数据库的记录  house已经接收部分表单数据
        // 设置标号
        house.setId(System.currentTimeMillis()+"");
        //设置用户编号
        Users user = (Users) session.getAttribute("user");
        house.setUserId(user.getId());
        //设置审核状态 0  如果表中有默认值 可不设
        house.setIspass(0);
        //设置是否删除  0
        house.setIsdel(0);
        //设置图片路径
        house.setPath(saveName);
        if (houseService.addHouse(house)>0){
            //发布成功
            return "redirect:goFabu";  //跳转页面
        }
        else{
            //成功上传的图片删除
            file.delete();
            return "redirect:goFabu";  //跳转页面
        }
    }

    @RequestMapping("selectAllHousesByUid")
    public String selectAllHousesByUid(HttpSession session,Model model,Integer page){
        Users user = (Users) session.getAttribute("user");
        PageInfo<House> pageInfo = houseService.selectAllHousesByUid(page==null?1:page, 1, user.getId());
        model.addAttribute("pageInfo", pageInfo);
        return "guanli";
    }

    @RequestMapping("selectHouse")
    public String selectHouse(String id,Model model){
        //查询类型，区域
        List<Type> types = typeService.allType();
        List<District> districts = districtService.allDistrict();
        House house = houseService.selectHouse(id);
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        model.addAttribute("house",house);
        return "upfabu";
    }

    @RequestMapping("upHouse")
    public String upHouse(String oldPic,House house,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile) {
        File file=null;
        //修不修改新图片
        if (pfile.getOriginalFilename().equals("")) {
            //保留图片
        } else {
            //上传新图片
            file = new File("D:\\images\\" + oldPic);
            try {
                pfile.transferTo(file);//保存
                house.setPath(oldPic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (houseService.upHouse(house) <= 0) {
            //成功上传的图片删除
            if (file != null) file.delete();
        }
        return "redirect:selectAllHousesByUid";  //跳转到查询用户出租房
    }
}
