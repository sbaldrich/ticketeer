package com.baldrichcorp.ticketeer.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baldrichcorp.ticketeer.config.spring.ServletContextConfiguration;

@ContextConfiguration
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

  @Configuration
  @Import(ServletContextConfiguration.class)
  @ComponentScan(basePackages = {
      "com.baldrichcorp.ticketeer" }, excludeFilters = @ComponentScan.Filter(Controller.class) )
  @EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = false)
  public static class SpringConfiguration {

    @Bean
    public DataSource dataSource() {
      return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
      Map<String, Object> properties = new HashMap<>();
      properties.put("javax.persistence.schema-generation.database.action", "create");
      HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
      adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(adapter);
      factory.setDataSource(dataSource());
      factory.setPackagesToScan("com.baldrichcorp.ticketeer.model");
      factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
      factory.setValidationMode(ValidationMode.NONE);
      factory.setJpaPropertyMap(properties);
      return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
      return new JpaTransactionManager(this.entityManagerFactoryBean().getObject());
    }

  }

}
