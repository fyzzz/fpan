FROM swr.cn-north-4.myhuaweicloud.com/fyzzz/java:8
COPY ./target/*.jar app.jar
ENV SERVER_PORT 80
ENV PAN_DATASOURCE_HOST mysql-server
ENTRYPOINT ["java","-jar","app.jar"]