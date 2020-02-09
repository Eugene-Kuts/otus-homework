FROM adoptopenjdk/openjdk11:alpine
MAINTAINER eugene.kuts@gmail.com
VOLUME /tmp
COPY /target/*.jar /opt/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]