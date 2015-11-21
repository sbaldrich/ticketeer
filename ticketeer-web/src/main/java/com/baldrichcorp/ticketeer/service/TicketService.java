package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.TicketOrder;

public interface TicketService {
  
  void process(TicketOrder order);
  
  Iterable<TicketOrder> getOrdersForUser(Long userId);
}
