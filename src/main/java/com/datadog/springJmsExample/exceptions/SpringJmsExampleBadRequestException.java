package com.datadog.springJmsExample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpringJmsExampleBadRequestException extends RuntimeException {

    public SpringJmsExampleBadRequestException(String message) {
        super(message);
	}
}
