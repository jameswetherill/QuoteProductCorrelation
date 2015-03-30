/**
 * 
 * Created : Mar 19, 2015
 * Creator : James Wetherill
 * QTP : com.jw.qtp.model
 */
package com.branded.holdings.qpc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "topic")
public class Topic extends BaseEntity{
		
	@Column
	private String description;	
	@Column
	private double weight;
	
	@ManyToOne
    @JoinColumn(name = "emotiontype_id")    
	private EmotionType type;
	
	public Topic(){}
	
	
	/**
	 * @return the emotion
	 */
	public String getEmotion() {
		return type.getName();
	}

	/**
	 * @return the value
	 */
	public String getdescription() {
		return description;
	}
	/**
	 * @param value the value to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setType(EmotionType type) {
        this.type = type;
    }

    public EmotionType getType() {
        return this.type;
    }
	
}
