package com.citiesconnected.exception;

public class CitiesConnectionValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CitiesConnectionValidationException(String errorMessage) {
		super(errorMessage);
	}

}
