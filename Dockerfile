FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /build

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x mvnw \
    && ./mvnw --no-transfer-progress package -DskipTests

FROM eclipse-temurin:25-jre-alpine

RUN apk add --no-cache curl \
    && addgroup -g 10001 -S app \
    && adduser -u 10001 -S -G app app

WORKDIR /app

COPY --from=builder /build/target/*.jar /app/app.jar

RUN chown -R app:app /app

USER app

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
