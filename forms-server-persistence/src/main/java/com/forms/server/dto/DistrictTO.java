package com.forms.server.dto;

/**
 *
 * @author Vikash
 */
public class DistrictTO extends SuperCommonDTO{
    
	private String name;
	public enum StateCode{
		KAR,MAH
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
