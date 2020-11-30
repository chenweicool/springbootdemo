package chen.springaop.service;

import chen.springaop.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author Chen
 * @Date 2020/3/18 11:49
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void getUser(User user) {
        if(user == null){
            throw  new RuntimeException("用户名不能为空");
        }
        System.out.println("用户Id:"+user.getId()+"用户名:"+user.getUserName()+"备注:"+user.getNote());
    }
}
