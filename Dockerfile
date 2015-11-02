FROM  tomcat:8-jre8
MAINTAINER Santiago Baldrich <santiago.baldrich@gmail.com>

COPY ./target/ticketeer-0.0.1-SNAPSHOT /usr/local/tomcat/webapps/ticketeer
CMD ["catalina.sh", "run"]
