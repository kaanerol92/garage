package com.kaanerol.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ SizeNotFoundException.class })
	public ResponseEntity<String> sizeNotFoundException(SizeNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ TicketNotFoundException.class })
	public ResponseEntity<String> ticketNotFoundException(TicketNotFoundException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ InvalidDefinitionException.class })
	public ResponseEntity<String> invalidDefinitionException(InvalidDefinitionException exception) {
		return new ResponseEntity<String>("Invalid json, fix it for request!", HttpStatus.BAD_REQUEST);
	}

}
