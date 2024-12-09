# 基础镜像
FROM openjdk:17-jdk-alpine

# 设置国内镜像源(中国科技大学镜像源)，修改容器时区(alpine镜像需安装tzdata来设置时区)，安装字体库(验证码)
RUN echo -e https://mirrors.ustc.edu.cn/alpine/v3.7/main/ \
    > /etc/apk/repositories  \
    && apk --no-cache add tzdata \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apk --no-cache add ttf-dejavu fontconfig

WORKDIR /app

# 在运行时自动挂载目录为匿名卷，提高可移植性
VOLUME /app/logs
VOLUME /app/upload
VOLUME /app/dataset

# 将构建的 Spring Boot 可执行 JAR 复制到容器中，重命名为 app.jar
COPY target/dehaze-java.jar app.jar

# 指定容器启动时执行的命令
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=prod"]

# 暴露容器的端口
EXPOSE 80
