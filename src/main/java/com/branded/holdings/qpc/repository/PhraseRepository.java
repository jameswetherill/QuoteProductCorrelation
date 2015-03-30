/**
 * 
 * Created : Mar 26, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.service
 */
package com.branded.holdings.qpc.repository;

import java.util.Collection;

import com.branded.holdings.qpc.model.Product;
import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.QuoteProductConnector;

/**
 * 
 */
public interface PhraseRepository {

	Collection<Quote> findQuotes();

	void saveQuote(Quote quote);
	
	Collection<Product> findProducts();

	void saveProduct(Product product);
	
	Collection<QuoteProductConnector> findQuoteProductConnectors();

	void saveQuoteProductConnector(QuoteProductConnector connector);


}
