package com.baldrichcorp.ticketeer.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baldrichcorp.ticketeer.model.TicketOrder;

@Configuration
public class AMQPConfiguration {
  
  private static final Logger logger = LoggerFactory.getLogger(AMQPConfiguration.class);
  private static final String workerQueueName = "workerQueue";

  @Bean
  public ConnectionFactory connectionFactory() {
    final CachingConnectionFactory factory = new CachingConnectionFactory();
    logger.info("Instantiating connection factory");
    factory.setHost("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setPort(5672);
    return factory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory());
    return template;
  }
  
  @Bean
  public AmqpAdmin amqpAdmin(){
    return new RabbitAdmin(connectionFactory());
  }
  

  @Bean
  public Queue queue() {
    return new Queue(workerQueueName);
  }
  
  @Bean
  public MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }
  
  @Bean
  public Receiver receiver(){
    return new Receiver();
  }
  
  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter adapter){
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(workerQueueName);
    container.setMessageListener(adapter);
    return container;
  }
  
  public static class Receiver{
    public void receiveMessage(TicketOrder order){
      logger.info("Received order for owner {}", order.getUser());
    }
  }
}
