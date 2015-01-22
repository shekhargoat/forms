package com.forms.server.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the appuser database table.
 * 
 */
@Entity
@Table(name="appuser")
@NamedQueries({
    @NamedQuery(name = Appuser.FIND_BY_NAME, query = "from Appuser a where a.username=:param"),
    @NamedQuery(name = Appuser.FIND_BY_SID,query = "from Appsuer a where hex(a.sid)=:param"),
    @NamedQuery(name = Appuser.FIND_BY_PWD,query = "from Appsuer a where a.password=:param"),
    @NamedQuery(name = Appuser.VERIFICATION_QUERY,query = "from Appuser a where a.activationKey=:param and hex(a.sid)=:param1")
})
public class Appuser extends BaseEntity{
	private static final long serialVersionUID = 1L;
        private static final String PREFIX="com.forms.server.entities.Appuser.";
        public static final String FIND_BY_NAME=PREFIX+"findByName";
        public static final String FIND_BY_SID=PREFIX+"findBySid";
        public static final String FIND_BY_PWD=PREFIX+"findByPassword";
        public static final String VERIFICATION_QUERY=PREFIX+"verificationQuery";
        

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
	private boolean isEnabled;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="preferred_language")
	private String preferredLanguage;

	private String username;

        @Column(name="activation_key")
	private String activationKey;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="appuser",fetch=FetchType.LAZY)
	private List<Form> forms;

	public Appuser() {
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

	public List<Form> getForms() {
		return this.forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

        public String getActivationKey() {
            return activationKey;
        }

        public void setActivationKey(String activationKey) {
            this.activationKey = activationKey;
        }
        
}