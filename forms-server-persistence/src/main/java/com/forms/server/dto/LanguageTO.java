package com.forms.server.dto;

/**
 *
 * @author Vikash
 */
public class LanguageTO extends SuperCommonDTO{
    
	private String name;
	private String languageCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
}
