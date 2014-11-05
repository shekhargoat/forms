package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the districts database table.
 * 
 */
@Entity
@Table(name="districts")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String sid;

	@Column(name="state_name")
	private String stateName;

	//bi-directional one-to-one association to SocietyName
	@OneToOne(mappedBy="district")
	private SocietyName societyName;

	public District() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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