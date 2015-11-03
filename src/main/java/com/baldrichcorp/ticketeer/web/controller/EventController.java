package com.baldrichcorp.ticketeer.web.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baldrichcorp.ticketeer.model.Event;
import com.baldrichcorp.ticketeer.service.EventService;

@Controller
public class EventController {

  @Autowired
  private EventService eventService;
  
  @RequestMapping(value = {"/", "/home"})
  public String list(Model model){
    Iterable<Event> upcomingEvents = eventService.getAll();
    Iterator<Event> it = upcomingEvents.iterator();
    model.addAttribute("highlight", it.next());
    model.addAttribute("events", upcomingEvents);
    return "event/list";
  }
}
