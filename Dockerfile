FROM ubuntu:latest AS build

RUN apt-get update -y
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven wget -y
COPY . .

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/BackEndBot-1.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]

