package com.forms.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.forms.server.dto.SuperCommonDTO;
/**
 * 
 * @author vikash
 *
 */
@Entity
@Table(name="language")
@NamedQueries({@NamedQuery(name=Language.FIND_ALL_Language, query="from Language l")})
public class Language extends BaseEntity{
	private static final String PREFIX="com.forms.server.entities.Language.";
    public static final String FIND_ALL_Language=PREFIX+"findAllLanguage";
	private String name;
	@Column(name="language_code")
	private String languageCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
