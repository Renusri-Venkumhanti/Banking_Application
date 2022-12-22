package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorDetails> AccountNotFoundException(AccountNotFoundException ex){
		ErrorDetails errorDetails = new ErrorDetails(0, ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ErrorDetails> TransactionException(TransactionException ex){
		ErrorDetails errorDetails = new ErrorDetails(0,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex){
		ErrorDetails errorDetails =  new ErrorDetails(0, ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
