package com.baldrichcorp.ticketeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.Event;
import com.baldrichcorp.ticketeer.repository.EventRepository;

@Service
public class DefaultEventService implements EventService{


  @Autowired
  private EventRepository eventRepo;
  
  @Override
  public Iterable<Event> getAll() {
    return eventRepo.getAll();
  }

  @Override
  public Event get(Long id) {
    return eventRepo.get(id);
  }

}
