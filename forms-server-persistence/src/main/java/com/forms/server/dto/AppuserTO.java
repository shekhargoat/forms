package com.forms.server.dto;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author vikash
 *
 */
public class AppuserTO extends SuperCommonDTO{
	
	private static final long serialVersionUID = 1L;

	private Date accountCreatedOn;

	private Date accountLastAccessed;

	private String contactPhoneNumber;

	private String firstName;

	private boolean isEnabled;

	private String lastName;

	private String password;

	private String preferredLanguage;

	private String username;
	
	private List<SecurityQuestionsTO> securityQuestionsTOs;

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

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
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

	public List<SecurityQuestionsTO> getSecurityQuestionsTOs() {
		return securityQuestionsTOs;
	}

	public void setSecurityQuestionsTOs(
			List<SecurityQuestionsTO> securityQuestionsTOs) {
		this.securityQuestionsTOs = securityQuestionsTOs;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
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
