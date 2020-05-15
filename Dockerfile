# Start with a base image containing Java runtime
FROM openjdk:13-jdk-alpine

WORKDIR /home/javarun/run

# Make port 8080 available to the world outside this container
EXPOSE 8080

COPY target/demo-0.0.1-SNAPSHOT.jar ./clarifai.jar

ENTRYPOINT exec java -jar /home/javarun/run/clarifai.jar