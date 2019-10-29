package com.team.house.pageController;

import com.team.house.entity.Users;
import com.team.house.service.UserService;
import com.team.house.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller(value = "pageUserController")
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private UserService userService;

    //注册功能
    @RequestMapping("regUser")
    public String regUser(Users users){
        int i = userService.regUser(users);
        if (i>0)
            return "login";
        else
        return "regs";
    }

    //验证用户名
    @RequestMapping("checkName")
    @ResponseBody
    public Map<String,Object> checkName(String name){
        Map<String,Object> map=new HashMap<String, Object>();
        int i = userService.checkName(name);
        map.put("result",i);
        return map;
    }
    @RequestMapping("quitLogin")
    public String quitLogin(HttpSession session){
        Map<String,Object> map=new HashMap<String, Object>();
        session.removeAttribute("user");
        return "login";
    }
    //登录功能
    @RequestMapping("loginByTerm")
    public String loginByTerm(String name, String password, HttpSession session){
        Users user = userService.loginByTerm(name, password);
        session.setAttribute("user",user);
        if (user!=null)
            return "redirect:getUserHouseNotDel";
        else
        return "login";
    }

    @RequestMapping("sendCode")
    @ResponseBody
    public String sendCode(String tel,HttpSession session){
        //生成六位数的验证码
        String str="0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <6 ; i++) {
            sb.append(str.charAt(random.nextInt(9))+"");
        }
        session.setAttribute("sb",sb);
        session.setMaxInactiveInterval(60);//60s存活时间
        String msg="请确保您的账号安全,您此次的验证码为:"+sb;
        int result = SmsUtil.sendMsg(tel, msg);
        System.out.println(msg);
        return "{\"result\":"+result+"}";

    }
    //登录功能
    @RequestMapping("loginByTerm2")
    public String loginByTerm2(String code, HttpSession session){
        Object sb = session.getAttribute("sb");
        if (code.equals(sb.toString()))
            return "redirect:getUserHouseNotDel";
        else
            return "login";
    }

}
