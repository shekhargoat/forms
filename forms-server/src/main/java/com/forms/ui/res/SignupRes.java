package com.forms.ui.res;

import java.util.List;
import com.forms.server.dto.SecurityQuestionsTO;
import com.forms.server.dto.SuperCommonDTO;
/**
 *
 * @author Vikash
 */
public class SignupRes extends SuperCommonDTO{

    private String contactPhoneNumer;

    private String firstName;

    private String lastName;

    private String password;

    private String preferredLanguage;

    private String username;
    
    private List<SecurityQuestionsTO> securityQuestionsTOs;
    
    private String securityAnswer;
    
    private boolean isEnabled;

    public String getContactPhoneNumer() {
        return contactPhoneNumer;
    }

    public void setContactPhoneNumer(String contactPhoneNumer) {
        this.contactPhoneNumer = contactPhoneNumer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getUsername() {
        return username;
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
    
}
