package com.baldrichcorp.ticketeer.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
  
  @Size(min = 3, max = 10, message = "{validation.login.handle}")
  private String handle;
  @NotEmpty(message = "{validation.login.password}")
  private String password;
 
  
  public String getHandle() {
    return handle;
  }
  
  public void setHandle(String handle) {
    this.handle = handle;
  }

  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
      
}
