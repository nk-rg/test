FROM maven:3.9.3-amazoncorretto-8 as util-b
WORKDIR /app/util
COPY ./util/pom.xml ./pom.xml
COPY ./util/src /app/util/src
RUN mvn clean install -DskipTests

FROM maven:3.9.3-amazoncorretto-8 as empleado-b
WORKDIR /app/empleado
COPY ./empleado/pom.xml ./pom.xml
COPY ./empleado/src ./src
COPY --from=util-b /root/.m2/repository /root/.m2/repository
RUN mvn package -DskipTests

FROM amazoncorretto:8
WORKDIR /app/empleado
COPY --from=empleado-b /app/empleado/target/empleado-0.0.1-SNAPSHOT.jar /app/empleado/empleado.jar
CMD ["java", "-jar", "empleado.jar"]