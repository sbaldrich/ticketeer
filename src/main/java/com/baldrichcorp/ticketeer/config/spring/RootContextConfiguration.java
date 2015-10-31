package com.baldrichcorp.ticketeer.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
    basePackages = {"com.baldrichcorp.ticketeer"}, 
    excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfiguration {

}
