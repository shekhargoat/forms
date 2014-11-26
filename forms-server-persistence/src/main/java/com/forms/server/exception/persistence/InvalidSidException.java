package com.forms.server.exception.persistence;

import com.forms.server.exception.ApplicationException;

public class InvalidSidException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478839877491477417L;
	
	public InvalidSidException() {
	}
	
	public InvalidSidException(String errorMessage,String uiErrorCode, Throwable throwable) {
		super(errorMessage, uiErrorCode, throwable);
	}
	
}
