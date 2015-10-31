package com.baldrichcorp.ticketeer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.baldrichcorp.ticketeer.service.AuthenticationService;
import com.baldrichcorp.ticketeer.web.form.SignupForm;

@Controller
public class AuthenticationController {
  
  @Autowired
  private AuthenticationService authService;
  
  @ResponseBody
  @RequestMapping("/")
  public String helloWorld(){
    return "Hello World!";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String signup(Model model){
    model.addAttribute("signupForm", new SignupForm());
    return "user/signup";
  }
  
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public View signup(SignupForm form){
    authService.register(form.getHandle(), form.getPassword());
    return new RedirectView("/", true, false);
  }
}
