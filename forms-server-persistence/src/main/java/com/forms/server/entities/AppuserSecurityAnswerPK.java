package com.forms.server.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * 
 * @author vikash
 *
 */
@Embeddable
public class AppuserSecurityAnswerPK implements Serializable{
    
	private static final long serialVersionUID = 8911654544495439040L;
	@Column(name="appuser_id")
	private Integer appuserId;
	@Column(name="security_question_id")
    private Integer securityQuestionId;

    public Integer getAppuserId() {
        return appuserId;
    }

    public void setAppuserId(Integer appuserId) {
        this.appuserId = appuserId;
    }

    public Integer getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(Integer securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }

    public AppuserSecurityAnswerPK() {
    }

    public AppuserSecurityAnswerPK(Integer appuserId, Integer securityQuestionId) {
        this.appuserId = appuserId;
        this.securityQuestionId = securityQuestionId;
    }
}
