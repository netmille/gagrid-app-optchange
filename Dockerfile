FROM openjdk:8-jdk-alpine
LABEL maintainer="turik@netmille.com"
COPY target/gagrid-app-optchange-0.1.0-beta.jar gagrid-app-optchange-0.1.0-beta.jar
ENTRYPOINT java -DAMOUNTCHANGE=$AMOUNTCHANGE -jar /gagrid-app-optchange-0.1.0-beta.jar
