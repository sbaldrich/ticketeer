package com.baldrichcorp.ticketeer.web.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baldrichcorp.ticketeer.model.UserPrincipal;

public class AuthenticationFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
  
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession(false);
    
    final Principal principal = UserPrincipal.getPrincipal(session);
    if(principal == null){
      httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
    }
    else{
      chain.doFilter(new HttpServletRequestWrapper(httpRequest){
        @Override
        public Principal getUserPrincipal(){
          return principal;
        }
      }, response);
    }
  }

  public void init(FilterConfig fConfig) throws ServletException {}

  public void destroy() {}
  
}
