package com.forms.server.exception;
/**
 * 
 * @author vikash
 *
 */
public class InvalidActivationKey extends ApplicationException {
	private static final long serialVersionUID = 3101410059508467087L;

	public InvalidActivationKey() {
    }

    public InvalidActivationKey(String errorMessage, String uiErrorCode, Throwable throwable) {
        super(errorMessage, uiErrorCode, throwable);
    }
    
}
