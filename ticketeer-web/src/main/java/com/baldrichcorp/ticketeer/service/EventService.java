package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.Event;

public interface EventService {
  Iterable<Event> getAll();
  
  Event get(Long id);
}
