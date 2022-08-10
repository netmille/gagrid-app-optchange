FROM openjdk:8-jdk-alpine
LABEL maintainer="turik@netmille.com"
COPY target/gagrid-app-optchange-0.0.1-SNAPSHOT.jar gagrid-app-optchange-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-DAMOUNTCHANGE=$AMOUNTCHANGE", "-jar","/gagrid-app-optchange-0.0.1-SNAPSHOT.jar"]
