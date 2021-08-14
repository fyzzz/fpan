FROM openjdk:8-jdk-alpine
FROM busybox:1.33
COPY ./target/*.jar app.jar
ENV SERVER_PORT 80
ENV PAN_DATASOURCE_HOST mysql-server
ENTRYPOINT ["java","-jar","/app.jar"]