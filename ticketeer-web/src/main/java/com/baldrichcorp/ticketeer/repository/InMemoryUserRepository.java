package com.baldrichcorp.ticketeer.repository;

import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.baldrichcorp.ticketeer.exception.EntityAlreadyExistsException;
import com.baldrichcorp.ticketeer.exception.EntityDoesNotExistException;
import com.baldrichcorp.ticketeer.model.User;

@Repository
public class InMemoryUserRepository implements UserRepository {

  private final HashMap<Long, User> userDatabase = new HashMap<>();
  private Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);
  private volatile long USER_ID_SEQUENCE = 1L;

  public InMemoryUserRepository() {
    this.userDatabase.put(1L, new User(1L, "admin", "admin", "admin@gmail.com"));
  }

  @Override
  public String getPasswordForUser(String handle) {
    return this.userDatabase.values().stream().filter(user -> user.getHandle().equals(handle)).findFirst().get()
        .getPassword();
  }

  @Override
  public void addUser(String handle, String password) {
    logger.info("Adding user {} to repository", handle);
    User user = new User();
    user.setHandle(handle);
    user.setPassword(password);
    add(user);
  }

  @Override
  public Collection<User> getAll() {
    return userDatabase.values();
  }

  @Override
  public User get(Long id) {
    return userDatabase.get(id);
  }

  @Override
  public void add(User entity) {
    if (userDatabase.containsKey(entity.getId()))
      throw new EntityAlreadyExistsException();
    long nextId;
    synchronized (this) {
      nextId = USER_ID_SEQUENCE++;
      entity.setId(nextId);
    }
    userDatabase.put(nextId, entity);
  }

  @Override
  public void update(User entity) {
    if (!userDatabase.containsKey(entity.getId())) {
      add(entity);
    } else {
      userDatabase.put(entity.getId(), entity);
    }
  }

  @Override
  public void delete(User entity) {
    deleteById(entity.getId());
  }

  @Override
  public void deleteById(Long id) {
    if (!userDatabase.containsKey(id)) {
      throw new EntityDoesNotExistException();
    }
  }

}
