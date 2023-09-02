# FROM docker.io/maven:3.9.3-eclipse-temurin-11-alpine
FROM docker.io/maven:3.9.3-eclipse-temurin-17-alpine
WORKDIR /home
ENV PORT=8080
EXPOSE $PORT
COPY . .
RUN mvn install
ENTRYPOINT ["mvn", "spring-boot:run"]