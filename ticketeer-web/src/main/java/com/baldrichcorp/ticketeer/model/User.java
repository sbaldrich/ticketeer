package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="customer", uniqueConstraints = {
    @UniqueConstraint(name = "unique_handle", columnNames= "handle") 
    },indexes = {
        @Index(name = "handle_index", columnList = "handle")
    })
public class User implements Serializable{

  private Long id;
  private String handle;
  private String password;
  private String email;

  public User() {}
  
  public User(Long id, String handle, String password, String email) {
    this.id = id;
    this.handle = handle;
    this.password = password;
    this.email = email;
  }

  @Id
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
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
   
  @Override
  public String toString(){
    return String.format("[User] = {%s, %s}", handle, email);
  }
}
