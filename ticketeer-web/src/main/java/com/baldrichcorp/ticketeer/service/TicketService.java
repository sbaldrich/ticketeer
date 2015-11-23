package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.UserPrincipal;

public interface TicketService {
  
  void process(TicketOrder order);
  
  Iterable<TicketOrder> getOrdersForUser(UserPrincipal user);
}
