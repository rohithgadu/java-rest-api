FROM openjdk:19
EXPOSE 8090
ADD build/libs/*.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java","-jar","java-rest-api-0.0.1-SNAPSHOT.jar"]
