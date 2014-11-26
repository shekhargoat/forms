package com.forms.server.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginForm.
 */
public class LoginForm implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -694651914138680625L;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}
