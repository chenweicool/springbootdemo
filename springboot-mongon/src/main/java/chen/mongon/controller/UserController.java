package chen.mongon.controller;
import chen.mongon.model.JsonResult;
import chen.mongon.model.User;
import chen.mongon.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Author Chen
 * @Date 2020/3/12 11:58
 * 用户的控制类
 **/
@RestController
@RequestMapping("/mongon")
public class UserController {

    @Autowired
   private  UserService userService;

    /**
     * 增加和修改的请求
     * @param user 请求的用户
     * @return 返回装的数据
     */
    @PostMapping("/save")
    public JsonResult save( User user){
       return userService.save(user);
    }

    /**
     * 查询所有的用户
     * @return
     */
    @GetMapping("/findAll")
    public List<User> getAll(){
        List<User> userList = userService.fingAll();
        return userList;
    }


    /**
     * 删除的方法
     * @param id 删除的id
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteUser(@PathVariable String id){
        return userService.DeleteUserById(id);
    }
}
