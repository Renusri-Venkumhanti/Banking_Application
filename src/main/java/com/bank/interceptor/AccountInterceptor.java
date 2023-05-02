package com.bank.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccountInterceptor implements HandlerInterceptor{
	public static final Logger log = LoggerFactory.getLogger(AccountInterceptor.class);

	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 log.info(".....pre handler.....");
		 log.info("Request uri "+request.getRequestURI());
		 request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		 log.info(".....post handler.....");
		 log.info("Request uri "+request.getRequestURI());
	}
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				@Nullable Exception ex) throws Exception {
		 log.info(".....After completion.....");
		 long time= System.currentTimeMillis()-(long)request.getAttribute("startTime");
		 log.info("ExecutionTime "+time);
		}
}
