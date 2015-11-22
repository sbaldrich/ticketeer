package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.User;

public interface TicketService {
  
  void process(TicketOrder order);
  
  Iterable<TicketOrder> getOrdersForUser(User user);
}
