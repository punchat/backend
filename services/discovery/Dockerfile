FROM openjdk:8-jre
MAINTAINER Aleksey Kobzev <a.kobzev@aol.com>

ADD ./target/discovery.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery.jar"]

EXPOSE 8761
