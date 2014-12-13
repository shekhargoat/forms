/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author SSD
 */
@Embeddable
public class AppuserSecurityAnswerPK implements Serializable{
    
    private Integer appuserId;
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
