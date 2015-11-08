package com.baldrichcorp.ticketeer.web.service;

import com.baldrichcorp.ticketeer.model.TicketOrder;

public interface TicketService {
  
  void process(TicketOrder order);
}
