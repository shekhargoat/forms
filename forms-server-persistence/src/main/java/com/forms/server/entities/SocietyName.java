package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * 
 * @author vikash
 *
 */
@Entity
@Table(name="society_name")
public class SocietyName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String address;

	@Column(name="district_id")
	private int districtId;

	private String sid;

	@Column(name="society_code")
	private String societyCode;

	@Lob
	@Column(name="society_name")
	private String societyName;

	//bi-directional many-to-many association to Section
	@ManyToMany(mappedBy="societyNames")
	private List<Section> sections;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="societyName")
	private List<Form> forms;

	//bi-directional one-to-one association to District
	@OneToOne
	@JoinColumn(name="id")
	private District district;

	public SocietyName() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSocietyCode() {
		return this.societyCode;
	}

	public void setSocietyCode(String societyCode) {
		this.societyCode = societyCode;
	}

	public String getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Form> getForms() {
		return this.forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public Form addForm(Form form) {
		getForms().add(form);
		form.setSocietyName(this);

		return form;
	}

	public Form removeForm(Form form) {
		getForms().remove(form);
		form.setSocietyName(null);

		return form;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}