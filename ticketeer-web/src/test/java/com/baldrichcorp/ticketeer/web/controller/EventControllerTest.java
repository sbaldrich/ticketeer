package com.baldrichcorp.ticketeer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baldrichcorp.ticketeer.config.spring.RootContextConfiguration;
import com.baldrichcorp.ticketeer.config.spring.ServletContextConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = { RootContextConfiguration.class, ServletContextConfiguration.class })
@WebAppConfiguration
public class EventControllerTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @BeforeClass
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void listEvents() throws Exception {
    mvc.perform(get("/event/")).andExpect(view().name("event/list"));
  }

  @Test(enabled = false)
  public void listEventsOnWelcome() throws Exception {
    mvc.perform(get("/")).andExpect(view().name("event/list"));
  }

}
