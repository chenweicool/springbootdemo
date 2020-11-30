package com.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Component("rbacService")
public class MyRabcService {
    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication){

        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails){
            //获取当前登录用户的UserDetails
            UserDetails userDetails = ((UserDetails)principal);

            //将当前请求的访问资源路径，如:"/syslog",包装成资源权限标识
            SimpleGrantedAuthority simpleGrantedAuthority
                    = new SimpleGrantedAuthority(request.getRequestURI());
            //判断用户已授权访问的资源中，是否包含“本次请求的资源”
            return userDetails.getAuthorities().contains(simpleGrantedAuthority);
        }
        return false;
    }
}
