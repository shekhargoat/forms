package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the forms database table.
 * 
 */
@Entity
@Table(name="forms")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="completed_on")
	private Date completedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@Column(name="form_language")
	private String formLanguage;

	@Column(name="form_name")
	private String formName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_accessed_on")
	private Date lastAccessedOn;

	private String sid;

	private String status;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	private Appuser appuser;

	//bi-directional many-to-one association to FinancialYear
	@ManyToOne
	@JoinColumn(name="financial_year_id")
	private FinancialYear financialYear;

	//bi-directional many-to-one association to SocietyName
	@ManyToOne
	@JoinColumn(name="society_id")
	private SocietyName societyName;

	public Form() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCompletedOn() {
		return this.completedOn;
	}

	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getFormLanguage() {
		return this.formLanguage;
	}

	public void setFormLanguage(String formLanguage) {
		this.formLanguage = formLanguage;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Date getLastAccessedOn() {
		return this.lastAccessedOn;
	}

	public void setLastAccessedOn(Date lastAccessedOn) {
		this.lastAccessedOn = lastAccessedOn;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public FinancialYear getFinancialYear() {
		return this.financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	public SocietyName getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(SocietyName societyName) {
		this.societyName = societyName;
	}

}