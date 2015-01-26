package com.forms.server.exception;

/**
 * 
 * @author vikash
 *
 */
public class ActivationKeyExpired extends ApplicationException {
    
	private static final long serialVersionUID = 4061536787238315185L;

	public ActivationKeyExpired() {
    }
	
    public ActivationKeyExpired(String errorMessage,String uiErrorCode, Throwable throwable) {
            super(errorMessage, uiErrorCode, throwable);
    }
    
}
