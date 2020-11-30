package chen.springaop.service;

import chen.springaop.model.User;

/**
 * @Author Chen
 * @Date 2020/3/18 12:25
 **/
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean validate(User user) {
        System.out.println("增强的接口:"+UserValidatorImpl.class.getSimpleName());
        return user != null;
    }
}
