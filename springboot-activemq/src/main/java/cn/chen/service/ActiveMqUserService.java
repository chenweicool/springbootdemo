package cn.chen.service;

import cn.chen.model.User;

/**
 * 接受用户的接口对象
 */
public interface ActiveMqUserService {
    void  sendUser(User user);
    void receiveUser(User user);
}
