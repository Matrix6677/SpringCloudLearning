# SpringCloudLearning
本项目基本涵盖了SpringCloud的所有组件的Demo，持续更新。
## 项目模块说明
为了便于区分，模块后面的数字表示端口号。
```
microservice                      
├─api                       提供公共的数据库实体、微服务接口
│
├─config-*                  分布式配置中心
│ ├─3344                    Config服务端，用于连接github
│ ├─client-3355             Config客户端，用于连接Config服务端
│ ├─eureka-7001             Config客户端，演示Eureka分布式配置
│ └─provider-dept-8001      Config客户端，演示接口提供方分布式配置
│
├─consumer-*                服务调用方
│ ├─dept-80                 Eureka客户端，演示服务消费及Ribbon负载均衡
│ ├─dept-feign              Eureka客户端，演示Feign面向接口的负载均衡及服务降级
│ └─hystrix-dashboard       演示服务监控
│ 
├─eureka-*                  服务注册中心集群
│
├─provider-dept-*           服务提供方
│ ├─8001~8003               Eureka客户端集群，演示服务注册
│ └─hystrix-8001            Eureka客户端，演示服务熔断
│
└─zuul-gateway-9527         Zuul路由网关
```
## Host修改
```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
127.0.0.1 myzuul.com
127.0.0.1 config-3344.com
127.0.0.1 client-config.com
127.0.0.1 gateway-9527.com
```