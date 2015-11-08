package com.baldrichcorp.ticketeer.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.TicketOrder;

@Service
public class DefaultTicketService implements TicketService {

  private static Logger logger = LoggerFactory.getLogger(TicketService.class);
  
  @Override
  public void process(TicketOrder order) {
    logger.info("Processing {} seats for event {}", order.getSeats(), order.getEvent().getName());
  }

}
