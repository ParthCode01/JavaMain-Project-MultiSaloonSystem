FROM eclipse-temurin:26-jre

WORKDIR /app

COPY target/SaloonManagement-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]