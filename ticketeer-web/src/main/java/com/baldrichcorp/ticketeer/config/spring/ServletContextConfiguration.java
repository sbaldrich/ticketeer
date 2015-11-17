package com.baldrichcorp.ticketeer.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.baldrichcorp.ticketeer", useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Controller.class) )
public class ServletContextConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setViewClass(JstlView.class);
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    final RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
    requestMappingHandlerMapping.setRemoveSemicolonContent(false);
    return requestMappingHandlerMapping;
  }
}
