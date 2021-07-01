FROM openjdk:11
COPY ./build/libs/*.jar /
ENTRYPOINT ["java","-jar","/com.github.kodomo-1.0.jar"]