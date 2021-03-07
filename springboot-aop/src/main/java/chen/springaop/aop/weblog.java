package chen.springaop.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface weblog {
    String name() default "";  // 接口的名称
    boolean intoDb() default  true; // 标识接口是否需要持久化
}
