package com.forms.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author vikash
 */
@Entity
@Table(name="state")
@NamedQueries({@NamedQuery(name=State.FIND_ALL_STATE,query="from State s")
})
public class State extends BaseEntity{
	private static final String PREFIX="com.forms.server.entities.State.";
    public static final String FIND_ALL_STATE=PREFIX+"findAllState";
    private String name;
    @Column(name="state_code")
    private String stateCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
