package com.forms.server.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author vikash
 */
@Entity
@Table(name = "appuser_security_questions_answer")
public class AppuserSecurityAnswer implements Serializable {

	private static final long serialVersionUID = -2066753951795013748L;

	@EmbeddedId
    private AppuserSecurityAnswerPK answerPK;
    
    private String answer;

    public AppuserSecurityAnswerPK getAnswerPK() {
        return answerPK;
    }

    public void setAnswerPK(AppuserSecurityAnswerPK answerPK) {
        this.answerPK = answerPK;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AppuserSecurityAnswer)) {
            return false;
        }
        AppuserSecurityAnswer other = (AppuserSecurityAnswer) object;
        if ((this.answerPK == null && other.answerPK != null) || (this.answerPK != null && !this.answerPK.equals(other.answerPK))) {
            return false;
        }
        return true;
    }
    
}
