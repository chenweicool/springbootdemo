package chen.springaop.service;

import chen.springaop.model.User;

/**
 * @Author Chen
 * @Date 2020/3/18 12:24
 *
 * 引入的增强类
 **/

public interface UserValidator {
    boolean validate(User user);
}
