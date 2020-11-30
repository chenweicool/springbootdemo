package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.exception.AjaxResult;
import com.security.exception.CustomException;
import com.security.exception.CustomType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义成功页面的响应的逻辑
 */
@Component
public class MyAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${spring.security.logintype}")
    private String loginType;

    private static ObjectMapper object = new ObjectMapper();

    /**
     * 登陆成功的响应实现
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset = utf-8");
            response.getWriter().write(
                    object.writeValueAsString(
                            AjaxResult.success()
                    )
            );
        }else{
             // 跳转到上一个的请求的页面中
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
