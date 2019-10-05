package com.stylefeng.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static String USER_INDEX = "index";
    private static String ADD_USER = "user/add_user";
    private static String UPDATE_USER = "user/update_user";
    private static String LOGIN_USER = "user/login_user";
    private static String NOAUTH_USER = "user/noauth_user";


    @RequestMapping(value = "/user")
    public String Login(Model model) {
        logger.info("===用户请求操作===");
        //HashMap<String, String> map = new HashMap<>();
        //map.put("user","张学友");
        //model.addAllAttributes(map);
        model.addAttribute("user", "全栈工程师");
        return USER_INDEX;
    }

    @RequestMapping(value = "/add")
    public String addUser(Model model) {
        logger.info("===添加数据===");
        return ADD_USER;
    }

    @RequestMapping(value = "/update")
    public String updateUser(Model model) {
        logger.info("===更新数据===");
        return UPDATE_USER;
    }

    @RequestMapping(value = "/login")
    public String loginUser() {
        logger.info("===请求登录===");

        return LOGIN_USER;
    }

    @RequestMapping(value = "/noAuth")
    public String noAuth() {
        logger.info("===未授权请求===");
        return NOAUTH_USER;
    }

    @RequestMapping(value = "/toLogin")
    public String toLoginUser(String user, String password, Model model) {
        logger.info("===登录认证===");
        /**
         * 使用shIro编写认证操作
         */
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user, password);
        if (token != null) {
            //执行登录操作
            try {
                subject.login(token);
                //登录成功
                return "redirect:/user";
            } catch (UnknownAccountException e) {
                //用户名不存在
                model.addAttribute("mag", "用户名不存在");
                return LOGIN_USER;

            } catch (IncorrectCredentialsException e) {
                //登录失败--密码错误
                model.addAttribute("mag", "密码错误");
                return LOGIN_USER;
            }
        } else {
            return LOGIN_USER;
        }
    }
}
