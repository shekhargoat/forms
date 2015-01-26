package com.forms.server.exception;
/**
 * 
 * @author vikash
 *
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 9016054302373139810L;

	private ErrorInfo errorInfo;
	
	public ApplicationException(){
		super();
	}
	
	public ApplicationException(String errorMessage,String uiErrorCode,Throwable throwable){
		super(errorMessage,throwable);
		errorInfo=new ErrorInfo(errorMessage, uiErrorCode);
	}

	public ApplicationException(String message, Throwable throwable) {
		this(message,message,throwable);
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	
}
