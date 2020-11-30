package cn.chen.service;

/**
 * @Author Chen
 * @Date 2020/3/9 19:56
 * Active的服务接口
 **/
public interface ActiveMqService {
    // 发送消息
    void sendMsg(String message);

    // 接受消息
    void receiveMsg(String message);
}
