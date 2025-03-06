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

VOLUME /app/log

COPY target/dehazing-java.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=prod"]

EXPOSE 80
