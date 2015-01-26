package com.forms.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the districts database table.
 * @author vikash
 */
@Entity
@Table(name="districts")
@NamedQueries({@NamedQuery(name = District.FIND_BY_STATE_CODE, query = "from District d where d.stateName=:stateCode")
})
public class District extends BaseEntity {
	private static final String PREFIX="com.forms.server.entities.District.";
    public static final String FIND_BY_STATE_CODE=PREFIX+"findDistrictsByStateCode";

    private String name;

	@Column(name="state_name")
	private String stateName;

	//bi-directional one-to-one association to SocietyName
	@OneToOne(mappedBy="district")
	private SocietyName societyName;

	public District() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public SocietyName getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(SocietyName societyName) {
		this.societyName = societyName;
	}

}