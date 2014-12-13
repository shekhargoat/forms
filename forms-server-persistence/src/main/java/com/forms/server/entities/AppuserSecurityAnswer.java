/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ag
 */
@Entity
@Table(name = "appuser_security_questions_answer")
public class AppuserSecurityAnswer implements Serializable {

    @EmbeddedId
    private AppuserSecurityAnswerPK answerPK;
    
    private String answer;

    /**
     * @return the answerPK
     */
    public AppuserSecurityAnswerPK getAnswerPK() {
        return answerPK;
    }

    /**
     * @param answerPK the answerPK to set
     */
    public void setAnswerPK(AppuserSecurityAnswerPK answerPK) {
        this.answerPK = answerPK;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
