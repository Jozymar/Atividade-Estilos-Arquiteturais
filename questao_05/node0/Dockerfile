FROM maven:3.6.0-jdk-8

ADD . /opt/app
RUN cd /opt/app && mvn compile package -DskipTests=true
RUN chmod +x /opt/app/start.sh