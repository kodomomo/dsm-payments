FROM openjdk:11
COPY ./build/libs/dsm-payments-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]