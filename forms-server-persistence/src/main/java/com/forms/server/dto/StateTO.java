package com.forms.server.dto;

/**
 *
 * @author Vikash
 */
public class StateTO extends SuperCommonDTO{
    
	private String name;
	private String stateCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
