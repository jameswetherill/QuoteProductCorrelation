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
@Table(name = "quote")
public class Quote extends Phrase {

	
	@Column(name = "author")
	@NotEmpty
	private String author;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "quote_topic", joinColumns = @JoinColumn(name = "quote_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
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

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

}
