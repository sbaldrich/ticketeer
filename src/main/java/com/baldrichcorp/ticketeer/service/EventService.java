package com.baldrichcorp.ticketeer.service;

import java.util.Collection;

import com.baldrichcorp.ticketeer.model.Event;

public interface EventService {
  Iterable<Event> getAll();
}
