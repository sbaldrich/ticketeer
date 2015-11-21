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
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.baldrichcorp.ticketeer.model.Event;
import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.User;
import com.baldrichcorp.ticketeer.service.EventService;
import com.baldrichcorp.ticketeer.service.TicketService;
import com.baldrichcorp.ticketeer.service.UserService;

@Controller
@RequestMapping(value = "event")
public class EventController {

  private static final Logger logger = LoggerFactory.getLogger(EventController.class);
  
  @Autowired
  private EventService eventService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private UserService userService;
  
  @RequestMapping(value = {"list", ""})
  public ModelAndView list(Model model){
    model.addAttribute("events", eventService.getAll());
    return new ModelAndView("event/list");
  }

  @RequestMapping(value = {"{id:\\d+}/view", "{id:\\d+}"})
  public ModelAndView view(Model model, @PathVariable long id){
    Event event = eventService.get(id);
    if(event == null){
      return listRedirectModelAndView();
    }
    model.addAttribute("event", event);
    return new ModelAndView("event/view");
  }
  
  @RequestMapping(value = "{eventId:\\d+}/purchase", method = RequestMethod.GET)
  public ModelAndView purchase(@PathVariable long eventId, 
      @RequestParam(value = "section", defaultValue = "general") String section, Model model){
    
    logger.info("Getting information about the {} tickets for event {}", section, eventId);
    
    TicketPurchaseForm form = new TicketPurchaseForm();
    Event event = eventService.get(eventId);
    
    if(event == null){
      return listRedirectModelAndView(); 
    }
    
    form.setEventId(event.getId());
    model.addAttribute("event", event);
    model.addAttribute("form", form);
    
    return new ModelAndView("/event/purchase");
  }
  
  @RequestMapping(value = "{eventId:\\d+}/purchase", method = RequestMethod.POST)
  public ModelAndView purchase(TicketPurchaseForm form, HttpSession session){
    User currentUser = userService.getByHandle( (String) session.getAttribute("username"));
    TicketOrder order = new TicketOrder(eventService.get(form.getEventId()), currentUser, form.getSeats());
    ticketService.process(order);

    return new ModelAndView("event/confirmation");
  }
  
  static class TicketPurchaseForm{
 
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
  
  public static ModelAndView listRedirectModelAndView(){
    return new ModelAndView(listRedirectView());
  }
  
  public static View listRedirectView(){
    return new RedirectView("/event/list", true, false);
  }
}
