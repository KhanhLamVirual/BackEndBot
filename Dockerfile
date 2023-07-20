FROM ubuntu

RUN apt-get update -y
RUN wget https://download.java.net/java/GA/jdk13.0.1/cec27d702aa74d5a8630c65ae61e4305/9/GPL/openjdk-13.0.1_linux-x64_bin.tar.gz
RUN tar -xvf openjdk-13.0.1_linux-x64_bin.tar.gz
RUN mv jdk-13.0.1 /opt/
RUN JAVA_HOME='/opt/jdk-13.0.1'
RUN PATH="$JAVA_HOME/bin:$PATH"
RUN export PATH
CMD ["java", "--version"]
RUN wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN tar -xvf apache-maven-3.6.3-bin.tar.gz
RUN mv apache-maven-3.6.3 /opt/
RUN M2_HOME='/opt/apache-maven-3.6.3'
RUN PATH="$M2_HOME/bin:$PATH"
RUN export PATH
CMD ["mvn", "--version"]

