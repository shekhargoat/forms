package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the section_has_questions database table.
 * 
 */
@Embeddable
public class SectionHasQuestionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="section_id")
	private int sectionId;

	@Column(name="question_id")
	private int questionId;

	public SectionHasQuestionPK() {
	}
	public int getSectionId() {
		return this.sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SectionHasQuestionPK)) {
			return false;
		}
		SectionHasQuestionPK castOther = (SectionHasQuestionPK)other;
		return 
			(this.sectionId == castOther.sectionId)
			&& (this.questionId == castOther.questionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sectionId;
		hash = hash * prime + this.questionId;
		
		return hash;
	}
}