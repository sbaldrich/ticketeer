package com.baldrichcorp.ticketeer.config.spring;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.baldrichcorp.ticketeer" },
  excludeFilters = @ComponentScan.Filter(Controller.class) )
@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = false)
@EnableJpaRepositories(
    basePackages = "com.baldrichcorp.ticketeer.repository.springdata", 
    entityManagerFactoryRef = "entityManagerFactoryBean", 
    transactionManagerRef = "jpaTransactionManager"
)
@PropertySource("classpath:jdbc.properties")
public class RootContextConfiguration{
  
}
