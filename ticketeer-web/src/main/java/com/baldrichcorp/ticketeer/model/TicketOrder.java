package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;

public class TicketOrder implements Serializable{
  
  private Long id;
  private Event event;
  private User owner;
  private short seats;
  
  public TicketOrder(){}
  
  public TicketOrder(Event event, User owner, short seats) {
    this.event = event;
    this.owner = owner;
    this.seats = seats;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Event getEvent() {
    return event;
  }
  
  public void setEvent(Event event) {
    this.event = event;
  }
  
  public User getOwner() {
    return owner;
  }
  
  public void setOwner(User owner) {
    this.owner = owner;
  }
  
  public short getSeats() {
    return seats;
  }
  
  public void setSeats(short seats) {
    this.seats = seats;
  }
    
}
