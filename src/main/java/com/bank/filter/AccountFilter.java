package com.bank.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
//@Order(1)
public class AccountFilter implements Filter{

	Logger log = LoggerFactory.getLogger(AccountFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Filter content before sending request to controller");
		log.info("Server name"+request.getServerName());
		log.info("Local port"+request.getLocalPort());
		
		HttpServletRequest httpServletRequest= (HttpServletRequest)request;
		log.info(httpServletRequest.getMethod());
		log.info( httpServletRequest.getRequestURI());
		chain.doFilter(request, response);
	}

}
