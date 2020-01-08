# kong-dashboard

### framework

springBoot + thymeleaf

### features

for kong version 1.1+

including swagger2-api

### install & run

java 8.0 +, maven 3 +

    
    java -DADMIN_PASSWORD=12345 \
        -DKONG_API_URL=http://172.16.8.31:8001 \
        -DROOT_LOG_LEVEL=error \
        -DKONG_LOG_LEVEL=debug \
        -DKONG_ENVIRONMENT_TYPE=DEV \
        -jar kong-dashboard-xxx.jar 
    
admin username：admin