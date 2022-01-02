FROM openjdk
FROM maven

WORKDIR /app

COPY . /app/


# RUN mvn -DskipTests clean dependency:list install
#
# COPY . .


EXPOSE 8080