package com.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多端登陆的实现，只允许一个设备进行登陆
 */
public class CustomerExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 403);
        map.put("message", "您的另一台设备已经登陆，或者您的session已经超时，请您重新登陆");

        String json = objectMapper.writeValueAsString(map);

        // 输出json信息
        event.getResponse().setContentType("application/json;charset=utf-8");
        event.getResponse().getWriter().write(json);
    }
}
