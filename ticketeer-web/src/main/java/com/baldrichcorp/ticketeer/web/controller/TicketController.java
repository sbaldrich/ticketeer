package com.baldrichcorp.ticketeer.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.baldrichcorp.ticketeer.model.Event;
import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.service.EventService;
import com.baldrichcorp.ticketeer.service.TicketService;
import com.baldrichcorp.ticketeer.service.UserService;

@Controller
public class TicketController {
  
private static final Logger logger = LoggerFactory.getLogger(EventController.class);
  
  @Autowired
  private EventService eventService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private UserService userService;
    
  @RequestMapping(value = "/secure/{eventId:\\d+}/purchase", method = RequestMethod.GET)
  public ModelAndView purchase(@PathVariable long eventId,
      @RequestParam(value = "section", defaultValue = "general") String section, Model model) {

    logger.info("Getting information about the {} tickets for event {}", section, eventId);

    TicketPurchaseForm form = new TicketPurchaseForm();
    Event event = eventService.get(eventId);

    if (event == null) {
      return EventController.listRedirectModelAndView();
    }

    form.setEventId(event.getId());
    model.addAttribute("event", event);
    model.addAttribute("form", form);

    return new ModelAndView("/secure/purchase");
  }

  @RequestMapping(value = "/secure/{eventId:\\d+}/purchase", method = RequestMethod.POST)
  public ModelAndView purchase(TicketPurchaseForm form, HttpSession session) {
    UserPrincipal currentUser = userService.getByHandle((String) session.getAttribute("username"));
    TicketOrder order = new TicketOrder(eventService.get(form.getEventId()), currentUser, form.getSeats());
    ticketService.process(order);

    return new ModelAndView("secure/confirmation");
  }

  static class TicketPurchaseForm {

    private long eventId;
    private short seats;
    private String email;

    public long getEventId() {
      return eventId;
    }

    public void setEventId(long eventId) {
      this.eventId = eventId;
    }

    public short getSeats() {
      return seats;
    }

    public void setSeats(short seats) {
      this.seats = seats;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

  }
}
