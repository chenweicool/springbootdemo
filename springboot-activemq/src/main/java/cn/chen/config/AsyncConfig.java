package cn.chen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

/**
 * @Author Chen
 * @Date 2020/3/9 16:27
 * 异步线程池的配置
 **/
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 定义线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        // 定义线程池
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        //  核心线程数
        threadPoolExecutor.setCorePoolSize(10);
        // 线程最大的线程数
        threadPoolExecutor.setMaxPoolSize(30);
        // 线程队列的最大线程数
        threadPoolExecutor.setQueueCapacity(2000);
        // 初始化
        threadPoolExecutor.initialize();

        return threadPoolExecutor;
    }
}
