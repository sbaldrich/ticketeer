package com.baldrichcorp.ticketeer.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class SignupForm {
  
  @Size(min = 3, max = 10, message = "{validation.signup.handle}")
  private String handle;
  @Email(message = "{validation.signup.email}")
  private String email;
  @Size(min = 6, message = "{validation.signup.password}")
  private String password;
 
  
  public String getHandle() {
    return handle;
  }
  
  public void setHandle(String handle) {
    this.handle = handle;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
      
}
