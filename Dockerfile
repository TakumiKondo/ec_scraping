FROM lpicanco/java11-alpine
ARG JAR_FILE=./build/libs/scraper-0.0.1-SNAPSHOT.jar
COPY . /app
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app/build/libs/scraper-0.0.1-SNAPSHOT.jar"]
