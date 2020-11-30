package com.security.service;

import com.security.domain.MyUserDetails;
import com.security.exception.CustomException;
import com.security.exception.CustomType;
import com.security.mapper.MyUserDetailsMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态加载用户的数据信息的实现
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private MyUserDetailsMapper myUserDetailsMapper;
    /**
     * 实现具体的逻辑的实现
     * @param username 用户名
     * @return   返回一个实体的用户
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = myUserDetailsMapper.findByUserName(username);
        if (myUserDetails == null) {
            throw new CustomException(CustomType.USER_NOT_EXIST);
        }

        // 获取用户的角色信息
        List<String> roleCodes = myUserDetailsMapper.findRoleByUserName(username);

        // 通过角色信息，获取用户的访问权限
        List<String> authorities = myUserDetailsMapper.findAuthorityByRoleCodes(roleCodes);

        // 为角色信息加上Role的这个值（spring_security的规范）
        roleCodes = roleCodes.stream().
                map(rc ->"ROLE_"+rc).collect(Collectors.toList());
        for (String roleCode : roleCodes) {
            System.out.println(roleCode);
        }
        // 合并角色的权限
        authorities.addAll(roleCodes);

        // 转成用逗号分隔的权限，为用户设置权限访问字符
        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", authorities)
                )
        );
        return myUserDetails;
    }
}
