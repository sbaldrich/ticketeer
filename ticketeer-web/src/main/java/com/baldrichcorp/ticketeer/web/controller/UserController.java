package com.baldrichcorp.ticketeer.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.service.TicketService;
import com.baldrichcorp.ticketeer.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
  
  @Autowired
  private TicketService ticketService;
  
  @Autowired
  private UserService userService;
  
  
  @RequestMapping(value = {"", "home"})
  public ModelAndView home(Model model, HttpSession session){
    UserPrincipal user = userService.getByHandle((String) session.getAttribute("username"));
    model.addAttribute("orders", ticketService.getOrdersForUser(user));
    return new ModelAndView("user/home");
  }
  
  public static ModelAndView userRedirectModelAndView(){
    return new ModelAndView(userRedirectView());
  }
  
  public static View userRedirectView(){
    return new RedirectView("/user/", true, false);
  }
}
