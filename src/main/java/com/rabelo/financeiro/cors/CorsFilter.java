package com.rabelo.financeiro.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.rabelo.financeiro.config.property.FinanceiroApiProperty;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE) 
public class CorsFilter{// implements Filter {	
	
	/**
	 * Foi Criado um Bean na Class ResourceServerConfig
	 * com isso essa Class foi Depreciada
	 */
	  
	@Autowired
	private FinanceiroApiProperty financeiroApiProperty;
	  
	
	  public void doFilter(ServletRequest request, ServletResponse
	  response, FilterChain chain) throws IOException, ServletException {
	  
	  HttpServletRequest req= (HttpServletRequest) request; HttpServletResponse
	  resp= (HttpServletResponse) response;
	  
	  resp.setHeader("Access-Control-Allow-Origin", financeiroApiProperty.getOriginPermitida());
	  resp.setHeader("Access-Control-Allow-Credentials", "true");
	  
	  if ("OPTIONS".equals(req.getMethod()) &&
	  financeiroApiProperty.getOriginPermitida().equals(req.getHeader("Origin")) ) {
	  
	  resp.setHeader("Access-Control-Allow-Mehtods",
	  "POST, GET, DELETE, PUT, PACTH, OPTIONS");
	  resp.setHeader("Access-Control-Allow-Headers",
	  "Authorization, Content-Type, Accept");
	  resp.setHeader("Access-Control-Max-Age", "3600");
	  
	  resp.setStatus(HttpServletResponse.SC_OK); 
	  }else { 
		  chain.doFilter(request, response);
		  
	  }
	  
	  }
	 
}
