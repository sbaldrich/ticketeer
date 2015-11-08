package com.baldrichcorp.ticketeer.model;

public class TicketOrder {
  
  private Event event;
  private String owner;
  private short seats;
  
  public TicketOrder(){}
  
  public TicketOrder(Event event, String owner, short seats) {
    this.event = event;
    this.owner = owner;
    this.seats = seats;
  }

  public Event getEvent() {
    return event;
  }
  
  public void setEvent(Event event) {
    this.event = event;
  }
  
  public String getOwner() {
    return owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public short getSeats() {
    return seats;
  }
  
  public void setSeats(short seats) {
    this.seats = seats;
  }
    
}
