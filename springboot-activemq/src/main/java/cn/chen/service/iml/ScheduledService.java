package cn.chen.service.iml;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author Chen
 * @Date 2020/3/10 16:53
 *
 * 测试定时任务的实现
 **/
@Service
public class ScheduledService {
    int count = 1;
    int count2 = 2;

    @Scheduled(fixedRate = 1000)
    @Async
    public void job1(){
        System.out.println(Thread.currentThread().getName()+"【job1】每秒钟执行一次，执行第"+count+"次");
        count++;
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void job2(){
        System.out.println(Thread.currentThread().getName()+"【job2】每秒钟执行一次，执行第"+count2+"次");
        count2++;
    }
}
