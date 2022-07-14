FROM openjdk:18
MAINTAINER thecoolersuptelov.github.com
EXPOSE 9000
COPY target/msgbackend-0.0.1-SNAPSHOT.jar msgbackend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/msgbackend-0.0.1-SNAPSHOT.jar"]
