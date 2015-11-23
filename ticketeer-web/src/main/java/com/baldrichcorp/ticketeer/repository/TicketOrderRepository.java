package com.baldrichcorp.ticketeer.repository;

import org.springframework.data.repository.CrudRepository;

import com.baldrichcorp.ticketeer.model.TicketOrder;
import com.baldrichcorp.ticketeer.model.UserPrincipal;

public interface TicketOrderRepository extends CrudRepository<TicketOrder, Long>{
  Iterable<TicketOrder> findByUser(UserPrincipal user);
}
