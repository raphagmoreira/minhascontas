FROM tomcat:8-jre8
MAINTAINER RGM

EXPOSE 9000

ENV TZ=America/Sao_Paulo

ADD jar/minhascontas.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "minhascontas.jar"]