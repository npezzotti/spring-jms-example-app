package com.datadog.springJmsExample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SpringJmsExampleResourceNotFoundException extends RuntimeException {

	public SpringJmsExampleResourceNotFoundException(String message) {
		super(message);
	}
}
