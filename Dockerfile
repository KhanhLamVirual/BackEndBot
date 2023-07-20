FROM ubuntu

RUN apt-get update -y
RUN apt-get install default-jdk -y
RUN apt-get install maven -y
EXPOSE 8080/tcp
EXPOSE 8080/udp
RUN mvn compile package spring-boot:run
