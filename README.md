## The Ticketeer Project

[![Build Status](https://travis-ci.org/sbaldrich/ticketeer.svg?branch=master)](https://travis-ci.org/sbaldrich/ticketeer)

A toy ticket application to compare development tools.

> More info coming soon

#### Running ticketeer

The application expects to find a Postgresql database configured as shown in the `jdbc.properties` (just use `t` on github to find the file). You can optionally run the `resources/populate.sql` file to add some lipsum events. Also, a simple *rabbitmq* server is expected to be listening on port `5672`.


If you have `docker` installed, you can optionally build the image using `docker build -t sbaldrich/ticketeer .` and run it using `docker run sbaldrich/ticketeer`.
> **This currently does not work!** ಠ_ಠ
