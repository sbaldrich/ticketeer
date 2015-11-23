package com.baldrichcorp.ticketeer.repository;

import org.springframework.data.repository.CrudRepository;

import com.baldrichcorp.ticketeer.model.UserPrincipal;

public interface UserRepository extends CrudRepository<UserPrincipal, Long>{
  UserPrincipal findByHandle(String handle);
}
