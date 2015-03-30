/**
 * 
 * Created : Mar 30, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.model
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
@Table(name = "quote_product_connector")
public class QuoteProductConnector extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "quote_id")
	private Quote quote;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	/**
	 * @return the quote
	 */
	public Quote getQuote() {
		return quote;
	}

	/**
	 * @param quote
	 *            the quote to set
	 */
	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

}
