package com.forms.server.dto;

import java.io.Serializable;
import java.util.Date;

public class AppuserTO extends SuperCommonDTO{
	
	private static final long serialVersionUID = 1L;

	private Date accountCreatedOn;

	private Date accountLastAccessed;

	private String contactPhoneNumer;

	private String firstName;

	private byte isEnabled;

	private String lastName;

	private String password;

	private String preferredLanguage;

	private String username;

	//bi-directional many-to-one association to Form
/*	@OneToMany(mappedBy="appuser",fetch=FetchType.LAZY)
	private List<Form> forms;*/

	public AppuserTO() {
	}

	public Date getAccountCreatedOn() {
		return this.accountCreatedOn;
	}

	public void setAccountCreatedOn(Date accountCreatedOn) {
		this.accountCreatedOn = accountCreatedOn;
	}

	public Date getAccountLastAccessed() {
		return this.accountLastAccessed;
	}

	public void setAccountLastAccessed(Date accountLastAccessed) {
		this.accountLastAccessed = accountLastAccessed;
	}

	public String getContactPhoneNumer() {
		return this.contactPhoneNumer;
	}

	public void setContactPhoneNumer(String contactPhoneNumer) {
		this.contactPhoneNumer = contactPhoneNumer;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(byte isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

/*	public List<Form> getForms() {
		return this.forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public Form addForm(Form form) {
		getForms().add(form);
		form.setAppuser(this);

		return form;
	}

	public Form removeForm(Form form) {
		getForms().remove(form);
		form.setAppuser(null);

		return form;
	}*/

}
