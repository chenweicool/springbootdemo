package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 模拟用户的登陆
 */
@Controller
public class LoginController {

    // 登陆的实现
    @PostMapping("/login")
    public String index(String username, String password) {
        return "index";
    }

    @GetMapping("/syslog")
    public String showLog(){
        return "syslog";
    }

    // 用户管理
    @GetMapping("/sysuser")
    public String addOrder() {
        return "sysuser"; //sysuser.html
    }

    // 具体业务一
    @GetMapping("/biz1")
    public String updateOrder() {
        return "biz1";  //biz1.html
    }

    // 具体业务二
    @GetMapping("/biz2")
    public String deleteOrder() {
        return "biz2";  //biz2.html
    }

    @GetMapping("/signout")
    public String logout(){
        return "logout";
    }
}
