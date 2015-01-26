package com.forms.server.exception;
/**
 * 
 * @author Vikash
 *
 */
public class ErrorInfo {
	
	public ErrorInfo(String errorMessage, String uiErrorCode) {
		super();
		this.errorMessage = errorMessage;
		this.uiErrorCode = uiErrorCode;
	}

	private String uiErrorCode;
	
	private String errorMessage;
	
	public String getErrorCode() {
		return uiErrorCode;
	}

	public void setErrorCode(String errorCode) {
		this.uiErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorInfo [errorCode=");
		builder.append(uiErrorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}
}
