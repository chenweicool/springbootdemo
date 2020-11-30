package com.security.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 测试加密方式的方法
 */
public class EncodepasswordTest{
    @Test
    public void passwordEncodeTest(){
        String rawPassword = "123456";
        PasswordEncoder Encodepassword = new BCryptPasswordEncoder();
        String matcherPassword = Encodepassword.encode(rawPassword);

        System.out.println("加密后的密码结果是：" + matcherPassword);
        System.out.println("校验的结果是：" + Encodepassword.matches(rawPassword, matcherPassword));

        String second = "12345456";
        System.out.println("一个陌生的密码校验的结果是：" + Encodepassword.matches(second, matcherPassword));
    }

}
