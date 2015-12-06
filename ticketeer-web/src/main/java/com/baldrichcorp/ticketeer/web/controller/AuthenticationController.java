
package com.baldrichcorp.ticketeer.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.service.AuthenticationService;
import com.baldrichcorp.ticketeer.web.form.LoginForm;
import com.baldrichcorp.ticketeer.web.form.SignupForm;
import com.baldrichcorp.ticketeer.web.form.ValidationForm;

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
  public ModelAndView signup(Model model){
    model.addAttribute("signupForm", new SignupForm());
    return signupModelAndView();
  }
  
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup(@Valid SignupForm form, Errors errors, Model model){
    if(errors.hasErrors()){
      return "auth/signup";
    }
    UserPrincipal user = new UserPrincipal();
    user.setHandle(form.getHandle());
    user.setEmail(form.getEmail());
    authService.saveUser(user, form.getPassword());
    model.asMap().remove("signupForm");
    return "redirect:/login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(Model model, HttpSession session, HttpServletRequest request){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return UserController.userRedirectModelAndView();
    
    return loginModelAndView(model);
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ModelAndView login(@Valid LoginForm form, Errors errors, Model model, HttpSession session, HttpServletRequest request){
    
    if(UserPrincipal.getPrincipal(session) != null)
      return new ModelAndView(UserController.userRedirectView());
    
    if(errors.hasErrors()){
      model.addAttribute("loginFailed", true);
      return loginModelAndView(model);
    }
    
    Principal principal = authService.authenticate(form.getHandle(), form.getPassword());
    
    if(principal != null){
      UserPrincipal.setPrincipal(session, principal);
      request.changeSessionId();
      model.addAttribute("username", form.getHandle());
      return new ModelAndView(UserController.userRedirectView());
    }
    else{
      model.addAttribute("loginFailed", true);
      return loginModelAndView(model);
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
  
  private ModelAndView signupModelAndView(){
    return new ModelAndView("auth/signup");
  }
  
  private ModelAndView loginModelAndView(Model model){
    model.addAttribute("loginform", new LoginForm());
    return new ModelAndView("auth/login");
  }
  
  
}
