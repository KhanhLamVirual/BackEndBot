FROM ubuntu

RUN apt-get update -y
RUN apt-get install default-jdk -y
RUN apt-get install maven wget -y
EXPOSE 8080/tcp
EXPOSE 8080/udp
RUN wget https://github.com/KhanhLamVirual/BackEndBot/raw/main/target/BackEndBot-1.jar
RUN java -jar BackEndBot-1.jar
