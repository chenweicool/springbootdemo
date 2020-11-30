package chen.springaop.controller;

import chen.springaop.model.User;
import chen.springaop.service.UserService;
import chen.springaop.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author Chen
 * @Date 2020/3/18 12:04
 * 切面的测试类
 **/
@Controller
@RequestMapping("/aop")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试切面类
     * @param id
     * @param userName
     * @param note
     * @return
     */
    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(int id,String userName,String note){
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        // 定义的切面编程类
        userService.getUser(user);
        return user;
    }

    @GetMapping("/vp")
    @ResponseBody
    public User validateAndGetUser(int id,String userName,String note){
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);

        // 强制类型转换
        UserValidator userValidator = (UserValidator) userService;
        if(userValidator.validate(user)){
            userService.getUser(user);
        }
        return user;
    }

    @GetMapping("/user")
    @ResponseBody
    public User validateAndGetUser(){
        User user = new User();
        user.setId(2);
        user.setUserName("chen");
        user.setNote("test");
        return user;
    }
}
