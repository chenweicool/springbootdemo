

**这个是springboot整合activeMq的model**

active的启动，在w10的d盘中
以实现异步通信。

两个Model
1. 一个是异步的实列：<br>
 涉及的类是    
 - AsyncConfig(配置线程池的类)
 - AsyncService（服务的类）
 - AsyncController（测试的启动类）
 
 运行启动这个项目： 在浏览器输入指定的Url 在控制台就可看到两个不同的线程输出
 
 主要用来检测异步线程池的调用
 
**2.就是springboot和actibve的简单整合**
 
 主要的类进行说明：
 
 -  model层    user   定义用户的基本信息
 - service    ActiveMqService   发送和接受信息的服务类    ActiveMqUserService  发送和接受的一个实体类
 -  controller     ActiveController   主要定义一个控制器进行发送消息和检验。输入一定的参数
 -  配置文件   application.properties  主要定义activemq的一些配置信息
 
 
      #active的地址
      spring.activemq.broker-url=tcp://192.168.192.137:61616
      # 用户民
      spring.activemq.user=admin
      # 密码
      spring.activemq.password=admin
      # 是否使用发布订阅模式，默认是false 即用的是点对点的模式
      spring.jms.pub-sub-domain=true
      # 默认的目的地址
      spring.jms.template.default-destination=activemq.default.destination
      # 是否启用连接池  设置为true好像会报错
      spring.activemq.pool.enabled=false
      # 连接池的最大数量配置
      spring.activemq.pool.max-connections=50
      
      # 定义mq对所有的包信任
      spring.activemq.packages.trust-all=true
消息发送的流程就是   定义一个发送目的地址，发送方进行发送消息。使用JMSLisen的注解来监控这个地址，并实现消息的接受。
 

测试了定时任务的测试