package com.security.config;

import com.security.exception.CustomerExpiredSessionStrategy;
import com.security.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyUserDetailService myUserDetailService;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 实现校验的逻辑认证
     * 使用内存来进行实现的
     * 数据库的登陆验证逻辑就在这里进行实现
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这是基于内存的访问方法的实现
   /*     auth.inMemoryAuthentication()
                .withUser("user")
                    .password(passwordEncoder().encode("123456"))
                    .roles("user")
                .and()
                    .withUser("admin")
                    .password(passwordEncoder().encode("123456"))
                    .roles("admin")
                .and()
                    .passwordEncoder(passwordEncoder());*/

        // 基于数据库动态的加载的实现
        auth.userDetailsService(myUserDetailService)
                .passwordEncoder(passwordEncoder());

    }

    /**
     * 基于fromLogin的认证
     * @param http 封装的http的协议
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()  // 验证的方式
                .loginPage("/login.html")  // 登陆的页面跳转
                .loginProcessingUrl("/login")  // 登陆成功的页面路由
                .usernameParameter("username")  // 表单获取相应的参数
                .passwordParameter("password")
               // .defaultSuccessUrl("/")  // 登陆成功后的页面跳转
               .successHandler(myAuthenticationSuccessHandler) // 跳转自定义的页面,响应json的格式
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/login","/signout").permitAll()

                // 这是基于内存的权限处理的方式
              /* .antMatchers("/", "/biz1", "/biz2")
                  .hasAnyAuthority("ROLE_user", "ROLE_admin")
                .antMatchers("/syslog").hasAuthority("/syslog")
                .antMatchers("/sysuser").hasAuthority("/sysuser")
                .anyRequest()
                .authenticated()*/

                // 基于数据库的处理方式
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .sessionManagement() // 添加session的管理
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)  // 实现一个多端的登陆功能
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new CustomerExpiredSessionStrategy());
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 限制不缓存内容实例
     * @param web  这个不走过滤器
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }
}
