# kong-dashboard
使用springBoot + thymeleaf开发；

废弃API对象，使用Service+Route，只兼容kong0.13+；

github上的node+angular版本更新过慢。

集成swagger2-api页面。

### install & run

java 8.0 +, maven 3 +

    
    java -DADMIN_PASSWORD=12345 -DKONG_API_URL=http://172.16.8.31:8001 -jar kong-dashboard-xxx.jar 
    
 默认用户名：admin