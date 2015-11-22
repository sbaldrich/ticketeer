package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TicketOrder implements Serializable{
  
  private Long id;
  private Event event;
  private User user;
  private short seats;
  
  public TicketOrder(){}
  
  public TicketOrder(Event event, User user, short seats) {
    this.event = event;
    this.user = user;
    this.seats = seats;
  }
  
  @Id
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
  public User getUser() {
    return user;
  }
  
  public void setUser(User owner) {
    this.user = owner;
  }
  
  public short getSeats() {
    return seats;
  }
  
  public void setSeats(short seats) {
    this.seats = seats;
  }
    
}
