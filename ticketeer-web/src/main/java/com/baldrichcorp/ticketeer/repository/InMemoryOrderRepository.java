package com.baldrichcorp.ticketeer.repository;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.baldrichcorp.ticketeer.exception.EntityAlreadyExistsException;
import com.baldrichcorp.ticketeer.exception.EntityDoesNotExistException;
import com.baldrichcorp.ticketeer.model.TicketOrder;

@Repository
public class InMemoryOrderRepository implements OrderRepository{

  private final HashMap<Long, TicketOrder> orderDatabase = new HashMap<Long, TicketOrder>();
  private volatile long ORDER_ID_SEQUENCE = 1L; 
  
  @Override
  public Collection<TicketOrder> getAll() {
    return orderDatabase.values();
  }

  @Override
  public TicketOrder get(Long id) {
    return orderDatabase.get(id);
  }

  @Override
  public void add(TicketOrder entity) {
    if(orderDatabase.containsKey(entity.getId())){
      throw new EntityAlreadyExistsException();
    }
    long nextId;
    synchronized (this) {
      nextId = ORDER_ID_SEQUENCE++;
      entity.setId(nextId);
    }
    orderDatabase.put(nextId, entity);
  }

  @Override
  public void update(TicketOrder entity) {
    if(!orderDatabase.containsKey(entity.getId())){
      add(entity);
    }
    else{
      orderDatabase.put(entity.getId(), entity);
    }
  }

  @Override
  public void delete(TicketOrder entity) {
    deleteById(entity.getId());    
  }

  @Override
  public void deleteById(Long id) {
    if(!orderDatabase.containsKey(id)){
      throw new EntityDoesNotExistException();
    }
    orderDatabase.remove(id);
  }

}
