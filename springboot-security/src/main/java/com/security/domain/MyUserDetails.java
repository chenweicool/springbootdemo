package com.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 用户的详细信息的类的实现
 */
@Component
public class MyUserDetails implements UserDetails {

    String password;
    String username;
    boolean accountNonExpired;
    boolean accountNonLocked;   //是否没被锁定
    boolean credentialsNonExpired;  //密码是否没过期
    boolean enabled;  //账号是否可用
    Collection<? extends GrantedAuthority> authorities;  //用户的权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 默认账户不过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;  // 默认账号不会被锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 默认账号不会过期
    }

    @Override
    public boolean isEnabled() {
        return enabled;    // 账号是否是可用的
    }
}
