package com.forms.server.exception.persistence;

import com.forms.server.exception.ApplicationException;

public class RecordNotFoundException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3478839877491477417L;
	
	public RecordNotFoundException() {
	}
	
	public RecordNotFoundException(String errorMessage,String uiErrorCode, Throwable throwable) {
		super(errorMessage, uiErrorCode, throwable);
	}

	public RecordNotFoundException(String message, Throwable throwable) {
		super(message, message, throwable);
	}
	
}
