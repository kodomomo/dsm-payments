FROM openjdk:11
COPY ./build/libs/DSM-Payments.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]