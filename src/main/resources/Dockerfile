FROM openjdk:17-alpine

RUN apk update && \
    apk upgrade

WORKDIR /app

COPY target/TaxService-0.0.1-SNAPSHOT.jar /app/TaxService.jar

EXPOSE 8080

CMD ["java", "-jar", "TaxService.jar"]