package com.baldrichcorp.ticketeer.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.service.AuthenticationService;
import com.baldrichcorp.ticketeer.web.form.SignupForm;

@Controller
@SessionAttributes("username")
public class AuthenticationController {
  
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
  
  @Autowired
  private AuthenticationService authService;
  
  @RequestMapping("/")
  public View home(){
    return homeView();
  }
  
  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String signup(Model model){
    model.addAttribute("signupForm", new SignupForm());
    return "auth/signup";
  }
  
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public ModelAndView signup(SignupForm form){
    UserPrincipal user = new UserPrincipal();
    user.setHandle(form.getHandle());
    authService.saveUser(user, form.getPassword());
    return loginView();
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, HttpSession session, HttpServletRequest request){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return "redirect:/";
    
    model.addAttribute("signupForm", new SignupForm());
    return "auth/login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView login(Model model, SignupForm form, HttpSession session, HttpServletRequest request){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return new ModelAndView(UserController.userRedirectView());
    
    Principal principal = authService.authenticate(form.getHandle(), form.getPassword());
    
    if(principal != null){
      UserPrincipal.setPrincipal(session, principal);
      request.changeSessionId();
      model.addAttribute("username", form.getHandle());
      return new ModelAndView(UserController.userRedirectView());
    }
    else{
      model.addAttribute("loginFailed", true);
      return new ModelAndView("auth/login");
    }
  }
  
  @RequestMapping("/logout")
  public View logout(HttpSession session, Model model){
    if(session != null){
      session.removeAttribute("username");
      session.invalidate();
      model.asMap().clear();
    }
    return homeView();
  }
 
  
  private View homeView(){
    return EventController.listRedirectView();
  }
  
  private ModelAndView loginView(){
    return new ModelAndView(new RedirectView("/login", true, false));
  }
}
