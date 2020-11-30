#### sercurity的登陆方式的讲解的使用：

##### 1 使用httpBasic的登陆方式来实现

  具体配置如下

```java
具体的配置就使用一个配置类继承自WebSecurityConfigurerAdapter这个方法就可以了。具体的示例如下
   
    @Override
      protected void configure(HttpSecurity http) throws Exception {
          http.httpBasic()//  基于httpBasic的认证方式
                  .and()   
                  .authorizeRequests()  // 所有请求都做拦截处理
                  .anyRequest()
                  .authenticated();
      }
```



具体的测试就是启动程序访问指定的接口，需要输入用户名和密码，密码在未配置的情况下，控制台有打印，也可以在配置文件中进行指定。默认的用户名是user

```java
spring 
 security:
    user:
      name: admin
      password: admin
```



#### 2.实现了多个用户的在线功能的实现以及功能添加实现。



具体看到第二章的部分，就是验证码的登陆还没看，具体的使用场景后面再说。

数据库表对应的是我w10表中的数据库表



#### 3，从数据库加载的动态模型的实现



#### 4， 运行项目：

##### 4.1   导入sql表，启动项目，

输入localhost：8888/login.html 进行访问，

用户名分别为： admin user

密码都是：     123456

#####  4.2   具体的权限配置的再serviceConfig的这个类中。

