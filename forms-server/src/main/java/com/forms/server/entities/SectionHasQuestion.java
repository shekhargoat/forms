package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the section_has_questions database table.
 * 
 */
@Entity
@Table(name="section_has_questions")
public class SectionHasQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SectionHasQuestionPK id;

	@Column(name="default_answer")
	private String defaultAnswer;

	@Column(name="is_mandatory")
	private byte isMandatory;

	public SectionHasQuestion() {
	}

	public SectionHasQuestionPK getId() {
		return this.id;
	}

	public void setId(SectionHasQuestionPK id) {
		this.id = id;
	}

	public String getDefaultAnswer() {
		return this.defaultAnswer;
	}

	public void setDefaultAnswer(String defaultAnswer) {
		this.defaultAnswer = defaultAnswer;
	}

	public byte getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(byte isMandatory) {
		this.isMandatory = isMandatory;
	}

}