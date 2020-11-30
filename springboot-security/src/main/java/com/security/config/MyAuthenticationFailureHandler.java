package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.exception.AjaxResult;
import com.security.exception.CustomType;
import com.security.mapper.MyUserDetailsMapper;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;

/**
 * 自定义失败的处理的的逻辑的实现
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${spring.security.logintype}")
    private String loginType;

    private  static ObjectMapper objectMapper = new ObjectMapper();
    //引入MyUserDetailsServiceMapper
    @Resource
    MyUserDetailsMapper myUserDetailsServiceMapper;

    //规则定义：1小时之内5次机会，第6次失败就触发限流行为（禁止访问）
    Set<RequestLimitRule> rules =
            Collections.singleton(RequestLimitRule.of(Duration.ofMinutes(60),5));
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);

    /**
     * 具体的实现登陆失败之后的逻辑
     * @param request  前端的请求
     * @param response  请求的响应
     * @param exception 抛出的异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //从request或request.getSession中获取登录用户名
        String userId = request.getParameter("username");

        //默认提示信息
        String errorMsg;
        if(exception instanceof LockedException){ //账户被锁定了
            errorMsg = "您已经多次登陆失败，账户已被锁定，请稍后再试！";
        }else if(exception instanceof SessionAuthenticationException){
            errorMsg = exception.getMessage();
        }else{
            errorMsg = "请检查您的用户名和密码输入是否正确";
        }

        //每次登陆失败计数器加1，并判断该用户是否已经到了触发了锁定规则
        boolean reachLimit = limiter.overLimitWhenIncremented(userId);
        if(reachLimit){ //如果触发了锁定规则，修改数据库 accountNonLocked字段锁定用户
            myUserDetailsServiceMapper.updateLockedByUserId(userId);
            errorMsg = "您多次登陆失败，账户已被锁定，请稍后再试！";
        }
        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(
                    objectMapper.writeValueAsString(
                            AjaxResult.error(CustomType.USER_INPUT_ERROR, "后端数据请求有误，请检查您的输入是否正确")
                    )
            );
        }else{
            response.setContentType("text/html;charset=utf-8");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
