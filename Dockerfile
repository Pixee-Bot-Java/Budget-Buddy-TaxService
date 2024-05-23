FROM alpine:latest

RUN apk update && apk upgrade && apk add openjdk17-jre

WORKDIR /app

COPY target/tax-service-0.0.1-SNAPSHOT.jar /app/tax-service.jar

EXPOSE 8080

CMD ["java", "-jar", "tax-service.jar"]
