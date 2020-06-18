package br.com.cadeup.blog.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cadeup.blog.model.Error;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	@ExceptionHandler(java.lang.NumberFormatException.class)
	public ResponseEntity<?> handleBadRequest(NumberFormatException exception) {
		
		logger.trace("Entering in handleBadRequest method");
		
		logger.error(String.format("NumberFormatException has occurred: %s", exception.getMessage()));
		
		logger.error("Bad Request: 400");
		
		return ResponseEntity.badRequest().body(new Error(true, "Invalid index").toString());
	}
	
}
