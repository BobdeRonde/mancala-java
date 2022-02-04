# syntax=docker/dockerfile:1

FROM openjdk:17-alpine3.13

WORKDIR /app

COPY gradle/ gradle
COPY gradlew settings.gradle ./
COPY buildSrc/ buildSrc
COPY config/ config
COPY code-coverage-report/ code-coverage-report
COPY api/src ./api/src
COPY domain/src ./domain/src
COPY api/build.gradle ./api
COPY domain/build.gradle ./domain
RUN ./gradlew build

CMD ["./gradlew", "run"]