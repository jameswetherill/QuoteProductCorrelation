/**
 * 
 * Created : Mar 25, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.model
 */
package com.branded.holdings.qpc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;


/**
 * 
 */
@Entity
@Table(name = "product")
public class Product extends Phrase {
	
	@Column(name = "retailer")
	@NotEmpty
	private String retailer;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_topic", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	private Set<Topic> topics;
	protected void setTopicsInternal(Set<Topic> topics) {
		this.topics = topics;
	}

	protected Set<Topic> getTopicsInternal() {
		if (this.topics == null) {
			this.topics = new HashSet<Topic>();
		}
		return this.topics;
	}

	@XmlElement
	public List<Topic> getTopics() {
		List<Topic> sortedSpecs = new ArrayList<Topic>(getTopicsInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition(
				"description", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}

	public int getNrOfTopics() {
		return getTopicsInternal().size();
	}

	public void addTopic(Topic topic) {
		getTopicsInternal().add(topic);
	}
	
	public void setRetailer(String retailer){
		this.retailer= retailer;
	}
	
	public String getRetailer(){
		return retailer;
	}
}
