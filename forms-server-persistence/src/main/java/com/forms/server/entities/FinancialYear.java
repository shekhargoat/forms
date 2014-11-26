package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the financial_year database table.
 * 
 */
@Entity
@Table(name="financial_year")
public class FinancialYear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="financial_year_duration")
	private String financialYearDuration;

	private String name;

	//bi-directional many-to-one association to Form
	@OneToMany(mappedBy="financialYear")
	private List<Form> forms;

	public FinancialYear() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinancialYearDuration() {
		return this.financialYearDuration;
	}

	public void setFinancialYearDuration(String financialYearDuration) {
		this.financialYearDuration = financialYearDuration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Form> getForms() {
		return this.forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public Form addForm(Form form) {
		getForms().add(form);
		form.setFinancialYear(this);

		return form;
	}

	public Form removeForm(Form form) {
		getForms().remove(form);
		form.setFinancialYear(null);

		return form;
	}

}