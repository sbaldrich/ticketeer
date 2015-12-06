package com.baldrichcorp.ticketeer.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
  @RequestMapping(value = "/secure/purchase", method = RequestMethod.GET)
  public ModelAndView purchase(@RequestParam(value = "event") long eventId,
      @RequestParam(value = "section", defaultValue = "general") String section, Model model) {

    logger.info("Getting information about the {} tickets for event {}", section, eventId);

    TicketPurchaseForm form = new TicketPurchaseForm();
    Event event = eventService.get(eventId);

    if (event == null) {
      return EventController.listRedirectModelAndView();
    }

    form.setEvent(event);
    form.setSeats((short)2);
    model.addAttribute("event", event);
    model.addAttribute("ticketPurchaseForm", form);

    return new ModelAndView("/secure/purchase");
  }

  @RequestMapping(value = "/secure/purchase", method = RequestMethod.POST)
  public ModelAndView purchase(@Valid TicketPurchaseForm form, Errors errors, Model model, HttpSession session) {
    if(errors.hasErrors()){
      return new ModelAndView("secure/purchase");
    }
    UserPrincipal currentUser = userService.getByHandle((String) session.getAttribute("username"));
    TicketOrder order = new TicketOrder(eventService.get(form.getEvent().getId()), currentUser, form.getSeats());
    ticketService.process(order);

    return new ModelAndView("secure/confirmation");
  }
  
  
  static class TicketPurchaseForm {
    
    @NotNull
    private Event event;
    @Range(min = 1, max = 10, message= "{validation.seats}")
    private short seats;
    @NotNull(message="{validation.signup.email}")
    @NotEmpty(message="{validation.signup.email}")
    @Email(message="{validation.signup.email}")
    private String email;

    public Event getEvent() {
      return event;
    }

    public void setEvent(Event event) {
      this.event = event;
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
