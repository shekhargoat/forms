package com.forms.ui.res;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SSD
 */
public class SignupRes {

    private String contactPhoneNumer;

    private String firstName;

    private String lastName;

    private String password;

    private String preferredLanguage;

    private String username;
    
    private String securityQuestionSid;
    
    private String securityAnswer;

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

    public String getSecurityQuestionSid() {
        return securityQuestionSid;
    }

    public void setSecurityQuestionSid(String securityQuestionSid) {
        this.securityQuestionSid = securityQuestionSid;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
    
}
