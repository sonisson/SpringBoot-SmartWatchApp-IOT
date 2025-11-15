FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/SmartWatch-3.2.4.jar SmartWatch-3.2.4.jar
ENTRYPOINT ["java", "-jar", "SmartWatch-3.2.4.jar"]