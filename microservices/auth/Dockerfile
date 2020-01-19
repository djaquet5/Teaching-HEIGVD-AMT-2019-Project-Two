FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash
COPY ./wait_for_it.sh /opt/
COPY ./swagger-spring-1.0.0.jar /opt/auth.jar
WORKDIR /opt
RUN chmod +x wait_for_it.sh
CMD ["java", "-jar", "auth.jar"]
