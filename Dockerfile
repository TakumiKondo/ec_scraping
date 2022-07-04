FROM lpicanco/java11-alpine
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} scraper-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","./build/libs/scraper-0.0.1-SNAPSHOT.jar"]
