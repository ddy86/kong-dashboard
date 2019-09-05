From harbor.corp.hongen.com/library/openjdk:8


ADD target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT java -DADMIN_PASSWORD=$ADMIN_PASSWORD -DKONG_API_URL=$KONG_API_URL -DROOT_LOG_LEVEL=$ROOT_LOG_LEVEL -DKONG_LOG_LEVEL=$KONG_LOG_LEVEL -jar /app.jar 

