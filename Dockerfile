FROM openjdk:17-alpine

RUN apk update && \
    apk upgrade

WORKDIR /app

COPY target/tax-service-0.0.1-SNAPSHOT.jar /app/tax-service.jar

EXPOSE 8080

CMD ["java", "-jar", "tax-service.jar"]
