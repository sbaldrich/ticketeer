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
  
  private static final String workerQueueName = "workerQueue";
  
  @Bean
  public ConnectionFactory connectionFactory(){
    final CachingConnectionFactory factory = new CachingConnectionFactory();
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setHost("192.168.99.100");
    factory.setPort(32769);
    return factory;
  }
  
  @Bean
  public RabbitTemplate rabbitTemplate() {
      RabbitTemplate template = new RabbitTemplate(connectionFactory());
      template.setRoutingKey(workerQueueName);
      template.setQueue(workerQueueName);
      return template;
  }
  
  @Bean
  public Queue queue(){
    return new Queue(workerQueueName);
  }
}
