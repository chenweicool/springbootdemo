package cn.chen.service.iml;

import cn.chen.model.User;
import cn.chen.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Chen
 * @Date 2020/3/9 20:09
 *
 * 接受用户的实现类
 **/
@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {

    @Autowired
    private JmsTemplate jmsTemplate;

    // 自定义地址
    public static final String myDestination = "my-destination";

    @Override
    public void sendUser(User user) {
        System.out.println("发送消息"+user);
        jmsTemplate.convertAndSend(myDestination,user);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveUser(User user) {
        System.out.println("接受到消息："+user);
    }
}
