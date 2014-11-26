package com.forms.server.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@Table(name="question")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="is_mandatory")
	private byte isMandatory;

	@Column(name="question_default_answer")
	private String questionDefaultAnswer;

	@Lob
	@Column(name="question_text")
	private String questionText;

	private String sid;

	public Question() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(byte isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getQuestionDefaultAnswer() {
		return this.questionDefaultAnswer;
	}

	public void setQuestionDefaultAnswer(String questionDefaultAnswer) {
		this.questionDefaultAnswer = questionDefaultAnswer;
	}

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}