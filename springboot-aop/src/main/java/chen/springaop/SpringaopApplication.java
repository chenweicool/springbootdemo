package chen.springaop;

import chen.springaop.aop.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"chen.springaop.aop"})
public class SpringaopApplication {

    // 定义切面
    @Bean(name = "myAspect")
    public MyAspect getMyAspect(){
        return new MyAspect();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringaopApplication.class, args);
    }



}
