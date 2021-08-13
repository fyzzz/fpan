FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar app.jar
ENV SERVER_PORT 8001
ENV PAN_DATASOURCE_HOST mysql-server
ENTRYPOINT ["java","-jar","/app.jar"]