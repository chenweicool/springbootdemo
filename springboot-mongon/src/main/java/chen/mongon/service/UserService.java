package chen.mongon.service;

import chen.mongon.model.JsonResult;
import chen.mongon.model.User;
import java.util.List;

/**
 * @Author Chen
 * @Date 2020/3/12 11:37
 * 用户的信息的具体操作
 **/
public interface UserService {
    JsonResult save(User user);
     JsonResult DeleteUserById(String  id);
     List<User> fingAll();
     User getUser(Long id);
}
