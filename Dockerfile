FROM gradle:8.0-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./

COPY src src

RUN gradle build -x test --no-daemon

FROM amazoncorretto:17-alpine-jdk

COPY --from=build /app/build/libs/manager-0.0.1.jar app.jar

EXPOSE  8080

ENTRYPOINT ["java", "-jar", "app.jar"]