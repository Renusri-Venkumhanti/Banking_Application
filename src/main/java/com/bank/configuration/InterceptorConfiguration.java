package com.bank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bank.interceptor.AccountInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	public void addInterceptors(InterceptorRegistry  registry) {
		registry.addInterceptor(execution());
	}
	
	@Bean
	public AccountInterceptor execution() {
		return new AccountInterceptor();
	}
}
