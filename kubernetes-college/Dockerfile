# $ cat Dockerfile
FROM openjdk:8-jre-alpine
COPY target/kubernetes-demo-0.0.1-SNAPSHOT.jar /kubernetes-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/kubernetes-demo-0.0.1-SNAPSHOT.jar"]

# $ docker build -t springboot-web:v1 .