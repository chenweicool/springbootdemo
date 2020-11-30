package com.security.mapper;

import com.security.domain.MyUserDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserDetailsMapperTest {
    @Resource
    private MyUserDetailsMapper myUserDetailsMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void findByUserName() {
        MyUserDetails myUserDetails = myUserDetailsMapper.findByUserName("admin");
        Assert.assertNotNull(myUserDetails);
    }

    @Test
    public void findRoleByUserName() {
        List<String> list = myUserDetailsMapper.findRoleByUserName("admin");
        Assert.assertNotNull(list);
    }

    @Test
    public void findAuthorityByRoleCodes() {
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("common");
        List<String> authority = myUserDetailsMapper.findAuthorityByRoleCodes(roles);
        authority.forEach(s -> System.out.println(s));
    }

    @Test
    public void contextLoad(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}