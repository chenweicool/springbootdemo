package cn.chen.controller;

import cn.chen.service.AsyncService;
import cn.chen.service.iml.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author Chen
 * @Date 2020/3/9 16:39
 * 异步信息的控制器
 **/
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    private AsyncService service;

    @Autowired
    private ScheduledService scheduledService;

    @GetMapping("/page")
    public String asyncPage(){
        System.out.println("请求的线程名称"+Thread.currentThread().getName());
        // 调用的异步服务
        service.generateReport();
        return "async";
    }

    @GetMapping("/schedule")
    public String asynSchedule(){
        scheduledService.job1();
        scheduledService.job2();
        return "async";
    }
}
