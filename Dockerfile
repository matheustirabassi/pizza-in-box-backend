FROM openjdk
FROM maven

WORKDIR /app

COPY . /app/

RUN mvn -DskipTests clean dependency:list install

EXPOSE 8080