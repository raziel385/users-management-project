FROM maven:3.6-jdk-11 as build_maven
WORKDIR /app

#copy pom
COPY pom.xml .
    
#copy source
COPY src ./src
RUN mvn clean package -Dmaven.test.skip


FROM openjdk:8-jdk-alpine
COPY --from=build_maven /app/target/users-management-0.0.1-SNAPSHOT.jar users-management-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/users-management-0.0.1-SNAPSHOT.jar"]