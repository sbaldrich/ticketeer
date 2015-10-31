package com.baldrichcorp.ticketeer.model;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;

public class UserPrincipal implements Principal, Cloneable, Serializable {

  private static final String PRINCIPAL_ATTRIBUTE = "com.baldrichcorp.user.principal";
  
  private final String username;

  public UserPrincipal(String username) {
    this.username = username;
  }

  @Override
  public String getName() {
    return this.username;
  }

  @Override
  public int hashCode() {
    return this.username.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof UserPrincipal && ((UserPrincipal) other).username.equals(this.username);
  }

  @Override
  protected UserPrincipal clone() {
    try {
      return (UserPrincipal) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e); // not possible
    }
  }

  @Override
  public String toString() {
    return this.username;
  }

  public static Principal getPrincipal(HttpSession session) {
    return session == null ? null : (Principal) session.getAttribute(PRINCIPAL_ATTRIBUTE);
  }

  public static void setPrincipal(HttpSession session, Principal principal) {
    session.setAttribute(PRINCIPAL_ATTRIBUTE, principal);
  }
}
