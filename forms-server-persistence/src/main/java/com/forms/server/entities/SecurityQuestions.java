package com.forms.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * 
 * @author vikash
 *
 */
@Entity
@Table(name = "security_questions")
@NamedQueries({
@NamedQuery(name = SecurityQuestions.FIND_ALL,query = "select s from SecurityQuestions s")})
public class SecurityQuestions extends BaseEntity{

	private static final long serialVersionUID = -3210881912803759797L;

	private static final String PREFIX="com.forms.server.entities.SecurityQuestions.";
    
    public static final String FIND_ALL=PREFIX+"findAll";
    
    @Column(name="name")
    private String question;
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurityQuestions)) {
            return false;
        }
        SecurityQuestions other = (SecurityQuestions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forms.server.entities.SecurityQuestions[ id=" + id + " ]";
    }


}
