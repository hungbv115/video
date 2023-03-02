FROM openjdk:11.0.1
RUN adduser  --disabled-password --home /home/video hungbv
USER hungbv
WORKDIR /home/video
ADD target/video-0.0.1-SNAPSHOT.jar /home/video/application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]
