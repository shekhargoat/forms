package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the appuser database table.
 * 
 */
@Entity
@Table(name="appuser")
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="account_created_on")
	private Date accountCreatedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="account_last_accessed")
	private Date accountLastAccessed;

	@Column(name="contact_phone_numer")
	private String contactPhoneNumer;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_enabled")
	private byte isEnabled;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="preferred_language")
	private String preferredLanguage;

	private String sid;

	private String username;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="appuser")
	private List<Form> forms;

	public Appuser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Form> getForms() {
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
	}

}