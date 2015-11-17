FROM  tomcat:8-jre8
MAINTAINER Santiago Baldrich <santiago.baldrich@gmail.com>

COPY ./ticketeer-web/target/ticketeer-web-0.0.1-SNAPSHOT /usr/local/tomcat/webapps/ticketeer
CMD ["catalina.sh", "run"]
