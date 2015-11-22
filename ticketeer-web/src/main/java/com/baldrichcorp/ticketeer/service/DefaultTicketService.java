package com.baldrichcorp.ticketeer.service;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.User;
import com.baldrichcorp.ticketeer.repository.OrderRepository;


@Service
public class DefaultTicketService implements TicketService {

  private static Logger logger = LoggerFactory.getLogger(TicketService.class);
  
  /*@Autowired
  private AmqpTemplate template;
  
  @Autowired
  private Queue queue;*/
  
  @Autowired
  private OrderRepository orderRepository;
  
  @Override
  public void process(TicketOrder order) {
    //logger.info("Enqueuing processing of order [ {} ] on {}", order.getUser(), queue.getName());
    //template.convertAndSend(queue.getName(), order);
    logger.info("Processing {} seats for event {}", order.getSeats(), order.getEvent().getName());
    orderRepository.add(order);
  }

  @Override
  public Iterable<TicketOrder> getOrdersForUser(User user) {
    return orderRepository.getAll().stream().filter(o -> o.getUser().getHandle().equals(user.getHandle())).collect(Collectors.toList());
  }

}
