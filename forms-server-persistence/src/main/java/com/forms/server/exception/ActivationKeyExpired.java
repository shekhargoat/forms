/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forms.server.exception;

/**
 *
 * @author ag
 */
public class ActivationKeyExpired extends ApplicationException {
    
    
    public ActivationKeyExpired() {
    }
	
    public ActivationKeyExpired(String errorMessage,String uiErrorCode, Throwable throwable) {
            super(errorMessage, uiErrorCode, throwable);
    }
    
}
