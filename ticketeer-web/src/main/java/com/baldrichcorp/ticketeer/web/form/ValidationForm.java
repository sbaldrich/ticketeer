package com.baldrichcorp.ticketeer.web.form;

import org.hibernate.validator.constraints.Email;

public class ValidationForm {

  @Email(message = "{invalid.email}")
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
