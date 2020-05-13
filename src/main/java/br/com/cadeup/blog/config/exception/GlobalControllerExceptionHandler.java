package br.com.cadeup.blog.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cadeup.blog.model.Error;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(java.lang.NumberFormatException.class)
	public ResponseEntity<?> handleBadRequest(NumberFormatException exception) {
		return ResponseEntity.badRequest().body(new Error(true, "Invalid index").toString());
	}
	
}
