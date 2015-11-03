package com.baldrichcorp.ticketeer.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.baldrichcorp.ticketeer.exception.EntityAlreadyExistsException;
import com.baldrichcorp.ticketeer.exception.EntityDoesNotExistException;
import com.baldrichcorp.ticketeer.model.Event;

@Repository
public class InMemoryEventRepository implements EventRepository {

  private final HashMap<Long, Event> eventDatabase = new HashMap<>();
  private volatile long EVENT_ID_SEQUENCE = 1L; 
  
  public InMemoryEventRepository() {
    add(new Event(null, LocalDateTime.now().plusDays(30), "In Flames at Royal Center", "Don't miss the greatest metal event of 2015"));
    add(new Event(null, LocalDateTime.now().plusDays(90), "Vicente Fernandez at Simón Bolivar Park", "The final tour"));
    add(new Event(null, LocalDateTime.now().plusDays(150), "Die Antwoord at Estéreo Picnic", "Let Ninja and ¥o-Landi mess with your head"));
  }
  
  @Override
  public Collection<Event> getAll() {
    return eventDatabase.values();
  }

  @Override
  public Event get(Long id) {
    return eventDatabase.get(id);
  }

  @Override
  public void add(Event entity) {
    if(eventDatabase.containsKey(entity.getId()))
      throw new EntityAlreadyExistsException();
    long nextId;
    synchronized(this){
      nextId = EVENT_ID_SEQUENCE++;
      entity.setId(nextId);
    }
    eventDatabase.put(entity.getId(), entity);
  }

  @Override
  public void update(Event entity) {
    if(!eventDatabase.containsKey(entity.getId())){
      add(entity);
    }
    else{
      eventDatabase.put(entity.getId(), entity);
    }
  }

  @Override
  public void delete(Event entity) {
    deleteById(entity.getId());
  }

  @Override
  public void deleteById(Long id) {
    if(!eventDatabase.containsKey(id)){
      throw new EntityDoesNotExistException();
    }
    eventDatabase.remove(id);
  }

}
