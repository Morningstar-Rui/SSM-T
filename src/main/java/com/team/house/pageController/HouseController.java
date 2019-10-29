package com.team.house.pageController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.ExtHouse;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.utils.PageParam;
import com.team.house.utils.SearchTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller(value = "houseController2")
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @RequestMapping("addHouse")
    public String addHouse(
            House house, HttpSession session,Model model,
            @RequestParam(value = "mfile",required = false) MultipartFile mfile){
        Users user =(Users) session.getAttribute("user");
        house.setUserId(user.getId());
        house.setContact(user.getName());

            /*long  startTime=System.currentTimeMillis();
            //System.out.println("fileName："+mfile.getOriginalFilename());
            String originalFilename=mfile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

            String path="G:/"+new Date().getTime()+ext;
            house.setPath(path);
            File newFile=new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            mfile.transferTo(newFile);
            long  endTime=System.currentTimeMillis();
            System.out.println("采用file.Transto的运行时间："+String.valueOf(endTime-startTime)+"ms");*/
            String realPath = "D:/uploads";
            /*判断文件不能为空*/
            if(mfile==null){
                model.addAttribute("result","文件不能为空！");
                return "fabu";
            }
            //getSize():获取大小
            /* if(mfile.getSize()>1048576){
                json.put("error","单文件上传不能超过1M！");
                return json.toJSONString();
            }*/
            //获取用户名后缀
            String originalFilename = mfile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成新名字
            String name = UUID.randomUUID().toString();
            //生成存放名字的路径
            String afterPath = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
            //创建文件夹路径
            File fdir = new File(realPath+"/"+afterPath);
            if(!fdir.exists()){
                fdir.mkdirs();
            }
            //生成回显的url路径:相对路径
            String url = "/uploads/"+afterPath+"/"+name+ext;
        System.out.println(url);
            //创建文件的路径
            File file = new File(fdir,name+ext);
        System.out.println(file);
            //上传文件
            try {
                mfile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("result","文件上传失败！");
            }
            house.setPath(url);
            house.setIsdel(0);
            house.setIspass(0);
            int i = houseService.addHouse(house);

            return "redirect:getUserHouseNotDel";
    }

    @RequestMapping("getUserHouseNotDel")
    public String getUserHouseNotDel(HttpSession session, Model model,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize){
        Users user = (Users) session.getAttribute("user");
        PageInfo<ExtHouse> pageInfo = houseService.getUserHouse(user.getId(), pageNum,pageSize,0);
        if (pageInfo.getList()!=null){
            model.addAttribute("pageInfo",pageInfo);
            return "guanli";
        }

        return "fabu";
    }
    @RequestMapping("getUserHouseIsDel")
    public String getUserHouseIsDel(HttpSession session, Model model,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize){
        Users user = (Users) session.getAttribute("user");
        PageInfo<ExtHouse> pageInfo = houseService.getUserHouse(user.getId(), pageNum,pageSize,1);
        if (pageInfo.getList()!=null){
            model.addAttribute("pageInfo",pageInfo);
            return "del";
        }

        return "fabu";
    }

    //逻辑删除 1:删除 0：未删除
    @RequestMapping("delHouseByIsDel")
    @ResponseBody
    public Map<String,Object> delHouseByIsDel(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
        int i = houseService.delHouse(id,1);
        map.put("result",i);
        return map;
    }
    @RequestMapping("delHouseByNotDel")
    @ResponseBody
    public Map<String,Object> delHouseByNotDel(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
        int i = houseService.delHouse(id,0);
        map.put("result",i);
        return map;
    }
    @RequestMapping("showHouse")
    public String showHouse( Model model,Integer id){
        ExtHouse extHouse = houseService.getHouseById(id);
        model.addAttribute("extHouse",extHouse);
        return "upfabu";
    }
    @RequestMapping("updateHouse")
    public String updateHouse( House house,String oldpic,@RequestParam(value = "mfile",required = false) MultipartFile mfile){
        try {
            if (!mfile.isEmpty()) {
                String realPath = "D:/uploads";
                //获取文件名后缀
                String originalFilename = mfile.getOriginalFilename();
                String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                //生成新名字
                String name = UUID.randomUUID().toString();
                //生成存放名字的路径
                String afterPath = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
                //创建文件夹路径
                File fdir = new File(realPath + "/" + afterPath);
                if (!fdir.exists()) {
                    fdir.mkdirs();
                }
                //生成回显的url路径:相对路径
                String url = "/uploads/" + afterPath + "/" + name + ext;
                //创建文件的路径
                File file = new File(fdir, name + ext);
                //上传文件
                mfile.transferTo(file);
                house.setPath(url);
                house.setIsdel(0);
                house.setIspass(0);
            }
            houseService.updateHouseInfo(house);
            //如果修改了图片就要删除旧图（不修改会无限制的制造垃圾文件）
            if (!mfile.isEmpty()) {
                File file = new File("D:" + oldpic);
                file.delete();
            }
            return "redirect:getUserHouseNotDel";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequestMapping("getAllHoesuBySearch")
    public String getAllHoesuBySearch(Model model, SearchTerm term){
        System.out.println(term.getPage()+"==="+term);
        PageInfo<ExtHouse> pageInfo = houseService.getHouseByTerm(term);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("term",term);
            return "list";
    }

}

