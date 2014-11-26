package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the section database table.
 * 
 */
@Entity
@Table(name="section")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="section_name")
	private String sectionName;

	private String sid;

	//bi-directional many-to-many association to SocietyName
	@ManyToMany
	@JoinTable(
		name="form_has_sections"
		, joinColumns={
			@JoinColumn(name="section_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="society_id")
			}
		)
	private List<SocietyName> societyNames;

	public Section() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public List<SocietyName> getSocietyNames() {
		return this.societyNames;
	}

	public void setSocietyNames(List<SocietyName> societyNames) {
		this.societyNames = societyNames;
	}

}