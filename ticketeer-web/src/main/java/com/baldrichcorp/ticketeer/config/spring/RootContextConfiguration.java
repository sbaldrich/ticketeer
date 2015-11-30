package com.baldrichcorp.ticketeer.config.spring;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan(basePackages = {
    "com.baldrichcorp.ticketeer" }, excludeFilters = @ComponentScan.Filter(Controller.class) )
@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = false)
@EnableJpaRepositories(basePackages = "com.baldrichcorp.ticketeer.repository", entityManagerFactoryRef = "entityManagerFactoryBean", transactionManagerRef = "jpaTransactionManager")
@PropertySource("classpath:jdbc.properties")
public class RootContextConfiguration {

  @Autowired
  private Environment environment;

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(this.messageSource());
    return validator;
  }

  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
    processor.setValidator(this.localValidatorFactoryBean());
    return processor;
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setCacheSeconds(-1);
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    messageSource.setBasenames("/WEB-INF/i18n/messages", "/WEB-INF/i18n/validation");
    return messageSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    Map<String, Object> properties = new HashMap<>();
    properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
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
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager jpaTransactionManager() {
    return new JpaTransactionManager(this.entityManagerFactoryBean().getObject());
  }

}
