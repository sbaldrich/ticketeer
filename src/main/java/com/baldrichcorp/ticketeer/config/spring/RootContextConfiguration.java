package com.baldrichcorp.ticketeer.config.spring;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;



@Configuration
@ComponentScan(
    basePackages = {"com.baldrichcorp.ticketeer"}, 
    excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfiguration {
  
}
