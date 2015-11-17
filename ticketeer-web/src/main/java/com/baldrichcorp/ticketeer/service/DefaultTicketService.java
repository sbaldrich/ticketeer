package com.baldrichcorp.ticketeer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.TicketOrder;


@Service
public class DefaultTicketService implements TicketService {

  private static Logger logger = LoggerFactory.getLogger(TicketService.class);
  
  @Autowired
  private AmqpTemplate template;
  @Autowired
  private Queue queue;
  
  @Override
  public void process(TicketOrder order) {
    logger.info("Enqueuing processing of order [ {} ] on {}", order.getOwner(), queue.getName());
    template.convertAndSend(queue.getName(), order);
    logger.info("Processing {} seats for event {}", order.getSeats(), order.getEvent().getName());
  }

}
