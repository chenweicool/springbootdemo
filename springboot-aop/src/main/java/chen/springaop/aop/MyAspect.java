package chen.springaop.aop;

import chen.springaop.service.UserValidator;
import chen.springaop.service.UserValidatorImpl;
import org.aspectj.lang.annotation.*;

/**
 * @Author Chen
 * @Date 2020/3/18 11:53
 * 定义切面
 **/
@Aspect
public class MyAspect {

    /**
     *DeclareParents 这个注解是用来引入新的类增强服务
     * value 指向你要增强的对象
     * defaultImpl 引入增强功能的类
     */
    @DeclareParents(value = "chen.springaop.service.UserServiceImpl+",
    defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;
    /**
     * 定义切点 对execution(* chen.springaop.service.UserServiceImpl.getUser(..)) 这个表达式说明如下：
     * execution 表示在执行的时候拦截的方法
     * * 表示任意返回类型的方法
     * chen.springaop.service.UserServiceImpl 表示目标对象的权限定名
     * getUser(..) 指定目标对象的方法，里面的.. 表示任意的参数表示
     */
    @Pointcut("execution(* chen.springaop.service.UserServiceImpl.getUser(..))")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(){
        System.out.println("before.......");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after.......");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning.......");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing.......");
    }
}
