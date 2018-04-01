FROM openjdk:8-jre
MAINTAINER Alexander Ivchenko <A.S.Ivchenko@yandex.ru>

ADD ./target/uaa.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/uaa.jar"]

EXPOSE 9999
