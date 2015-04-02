/**
 * 
 * Created : Mar 26, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.model
 */
package com.branded.holdings.qpc.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 */
@MappedSuperclass
public class Phrase extends BaseEntity {

	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	
	@Column(name = "proccessed" )
	@NotEmpty
	private Integer proccessed;


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
