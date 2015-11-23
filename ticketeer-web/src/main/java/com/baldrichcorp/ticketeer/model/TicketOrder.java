package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TicketOrder implements Serializable{
  
  private Long id;
  private Event event;
  private UserPrincipal user;
  private short seats;
  
  public TicketOrder(){}
  
  public TicketOrder(Event event, UserPrincipal user, short seats) {
    this.event = event;
    this.user = user;
    this.seats = seats;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  public Event getEvent() {
    return event;
  }
  
  public void setEvent(Event event) {
    this.event = event;
  }
  
  @ManyToOne
  public UserPrincipal getUser() {
    return user;
  }
  
  public void setUser(UserPrincipal owner) {
    this.user = owner;
  }
  
  public short getSeats() {
    return seats;
  }
  
  public void setSeats(short seats) {
    this.seats = seats;
  }
    
}
