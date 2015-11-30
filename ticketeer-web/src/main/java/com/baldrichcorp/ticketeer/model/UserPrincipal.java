package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;
import java.security.Principal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "unique_handle", columnNames= "handle") 
    },indexes = {
        @Index(name = "handle_index", columnList = "handle")
    })
public class UserPrincipal implements Principal, Cloneable, Serializable {

  private static final String PRINCIPAL_ATTRIBUTE = "com.baldrichcorp.user.principal";
  
  private Long id;
  @NotNull(message="{validation.fieldNotNull}")
  private String handle;
  @NotNull(message="{validation.fieldNotNull}")
  @Email(message="{validation.email}")
  private String email;
  private byte[] password;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public byte[] getPassword() {
    return password;
  }

  public void setPassword(byte[] password) {
    this.password = password;
  }

  @Override
  public int hashCode() {
    return this.handle.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof UserPrincipal && ((UserPrincipal) other).handle.equals(this.handle);
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
    return this.handle;
  }

  public static Principal getPrincipal(HttpSession session) {
    return session == null ? null : (Principal) session.getAttribute(PRINCIPAL_ATTRIBUTE);
  }

  public static void setPrincipal(HttpSession session, Principal principal) {
    session.setAttribute(PRINCIPAL_ATTRIBUTE, principal);
  }

  @Override
  @Transient
  public String getName() {
    return this.handle;
  }
}
