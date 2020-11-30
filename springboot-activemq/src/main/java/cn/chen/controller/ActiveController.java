package cn.chen.controller;
import cn.chen.model.User;
import cn.chen.service.ActiveMqService;
import cn.chen.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Chen
 * @Date 2020/3/9 20:16
 *定一个发送消息和接受消息的控制器
 **/
@Controller
@RequestMapping("/activemq")
public class ActiveController {

    @Autowired
    private ActiveMqUserService activeMqUserService ;

    @Autowired
    private ActiveMqService activeMqService;

    /**
     * 普通消息的发送
     * @param message
     * @return
     */
    @ResponseBody
    @GetMapping("/msg")
    public Map<String,Object> msg(String message){
        activeMqService.sendMsg(message);
        return result(true,message);
    }


    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> userMsg(Long id,String userName,String note){
        User user  = new User(id,userName,note);
        activeMqUserService.sendUser(user);
        return result(true,user);
    }
    private Map<String, Object> result(boolean success, Object message) {
        Map<String,Object> result = new HashMap<>();
        result.put("successs",success);
        result.put("message",message);
        return result;
    }

}
