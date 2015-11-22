package com.baldrichcorp.ticketeer.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(indexes={
    @Index(name = "event_name", columnList = "name")
})
public class Event implements Serializable{

  private Long id;
  private LocalDateTime date;
  private String name;
  private String description;
 
  public Event() {}
  
  public Event(Long id, LocalDateTime date, String name, String description){
    this.id = id;
    this.date = date;
    this.name = name;
    this.description = description;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }
  
  public void setDate(LocalDateTime date) {
    this.date = date;
  }
  
  public String getName() {
    return name;
  }
 
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
    
}
