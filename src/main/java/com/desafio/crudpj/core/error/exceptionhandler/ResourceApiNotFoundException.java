package com.desafio.crudpj.core.error.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceApiNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceApiNotFoundException(String message) {
		super(message);
	}
}
