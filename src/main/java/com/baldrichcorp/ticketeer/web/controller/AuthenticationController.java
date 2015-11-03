package com.baldrichcorp.ticketeer.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.service.AuthenticationService;
import com.baldrichcorp.ticketeer.web.form.SignupForm;

@Controller
public class AuthenticationController {
  
  @Autowired
  private AuthenticationService authService;
  
  @RequestMapping("/secure/home")
  public String helloWorld(){
    return "secure/hello";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String signup(Model model){
    model.addAttribute("signupForm", new SignupForm());
    return "user/signup";
  }
  
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public View signup(SignupForm form){
    authService.register(form.getHandle(), form.getPassword());
    return loginView();
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, HttpSession session){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return "redirect:/";
    
    model.addAttribute("signupForm", new SignupForm());
    return "user/login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public View login(Model model, SignupForm form, HttpSession session, HttpServletRequest request){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return secureHomeView();
    
    Principal principal = authService.authenticate(form.getHandle(), form.getPassword());
    
    if(principal != null){
      UserPrincipal.setPrincipal(session, principal);
      request.changeSessionId();
      return secureHomeView();
    }
    else{
      model.addAttribute("loginFailed", true);
      return loginView();
    }
  }
  
  @RequestMapping("/logout")
  public View logout(HttpSession session){
    if(session != null)
      session.invalidate();
    return homeView();
  }
 
  private View secureHomeView(){
    return new RedirectView("secure/home", true, false);
  }
  
  private View homeView(){
    return new RedirectView("/home", true, false);
  }
  
  private View loginView(){
    return new RedirectView("/login", true, false);
  }
}
