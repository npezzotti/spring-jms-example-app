package com.datadog.springJmsExample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpringJmsExampleControllerExceptionHandler {

	@ExceptionHandler(value = SpringJmsExampleResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage handleNotFound(SpringJmsExampleResourceNotFoundException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage());
		return message;
	}
	
	@ExceptionHandler(value = SpringJmsExampleBadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleBadRequest(SpringJmsExampleBadRequestException e) {
		ErrorMessage message = new ErrorMessage(e.getMessage());
		return message;
	}
}
