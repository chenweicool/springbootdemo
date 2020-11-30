package cn.chen.service.iml;

import cn.chen.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
/**
 * @Author Chen
 * @Date 2020/3/9 16:36
 * 异步方法的实现
 * **/
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void generateReport() {
        System.out.println("报表名称"+"["+Thread.currentThread().getName()+"]");
    }
}
