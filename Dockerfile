

FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/cave-enki-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} cave-enki.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","cave-enki.jar"]

# Create builder stage for build application.
#FROM maven:3.5.2-jdk-8-alpine as builder
#
#MAINTAINER Hadrien Fongue
#
#WORKDIR /app
#
#COPY . /app
#
## Build maven application
#RUN mvn clean package
#
#RUN mv target/*.jar cave-enki.jar
#
## Reduce image size
#FROM openjdk:latest
#
#WORKDIR /app
#
#COPY --from=builder /app/cave-enki.jar /app/cave-enki.jar
#
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cave-enki.jar"]