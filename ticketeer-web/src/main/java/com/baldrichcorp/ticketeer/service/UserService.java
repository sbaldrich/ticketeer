package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.User;

public interface UserService {
  User getByHandle(String handle);
}
