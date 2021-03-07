package chen.springaop.controller;

import chen.springaop.aop.weblog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {
    @GetMapping("/getOne")
    @weblog(name = "查询", intoDb = true)
    public String getOne(Long id, String name) {
        return "1234";
    }

}
