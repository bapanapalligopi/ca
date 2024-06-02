FROM eclipse-temurin:17

LABEL maintainer="abdabd1703@gmail.com"

WORKDIR /app

COPY chatanywhere/target/chatanywhere-0.0.1-SNAPSHOT.jar /app/chatanywhere.jar

ENTRYPOINT ["java", "-jar", "chatanywhere.jar"]
