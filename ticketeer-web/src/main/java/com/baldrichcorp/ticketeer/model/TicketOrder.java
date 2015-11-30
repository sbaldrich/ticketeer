package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class TicketOrder implements Serializable{
  
  private Long id;
  
  @NotNull(message="{validation.fieldNotNull}")
  @Valid
  private Event event;
  @NotNull(message="{validation.fieldNotNull}")
  @Valid
  private UserPrincipal user;
  @Range(min = 1, max = 100, message="{validation.numeric.positive}")
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
