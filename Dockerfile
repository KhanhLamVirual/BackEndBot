FROM ubuntu

RUN apt-get update -y
RUN apt-get install default-jdk -y
RUN apt-get install maven -y
CMD ["mvn", "--version"]
CMD ["java", "--version"]
