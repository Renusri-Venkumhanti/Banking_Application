package com.bank.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.filter.AccountFilter;

@Configuration
public class FilterConfiguration {
	
	@Bean
	public FilterRegistrationBean<AccountFilter> registration(){
		FilterRegistrationBean<AccountFilter> registrationBean = new FilterRegistrationBean<AccountFilter>();
		
		registrationBean.setFilter(new AccountFilter());
		registrationBean.addUrlPatterns("/account/*");
		return registrationBean;
	}
	

}
