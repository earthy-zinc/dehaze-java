# SpringBoot单体应用部署Dockerfile
FROM openjdk:17

# /data 目录就会在运行时自动挂载为匿名卷，任何向 /data 中写入的信息都不会记录进容器存储层
VOLUME /data

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar", "/app.jar"]

EXPOSE 80
