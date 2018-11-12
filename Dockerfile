From harbor.corp.hongen.com/library/openjdk:8

ADD kong-dashboard-v1.1.2.jar 

EXPOSE 8080

ENTRYPOINT ["java","-DADMIN_PASSWORD=$ADMIN_PASSWORD -DKONG_API_URL=$KONG_API_URL","-jar","/kong-dashboard-v1.1.2.jar"]

