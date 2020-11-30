package cn.chen.service.iml;

import cn.chen.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Chen
 * @Date 2020/3/9 19:59
 * Active的实现类
 **/
@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息"+message);
        jmsTemplate.convertAndSend(message);
    }

    /**
     * 使用注解接受发送过来的消息
     * @param message
     */
    @Override
    @JmsListener(destination ="${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接受到消息"+message);
    }
}
