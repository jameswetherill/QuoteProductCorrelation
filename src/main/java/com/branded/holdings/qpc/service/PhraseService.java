/**
 * 
 * Created : Mar 25, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.service
 */
package com.branded.holdings.qpc.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.branded.holdings.qpc.model.Product;
import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.QuoteProductConnector;

/**
 * 
 */
public interface PhraseService {

	Collection<Quote> findQuotes() throws DataAccessException;
	
	void saveQuote(Quote quote) throws DataAccessException;
	
	void processQuote(Quote quote);
	
	Collection<Product> findProducts() throws DataAccessException;
	void saveProduct(Product product) throws DataAccessException;
	void processProduct(Product product);
	
	Collection<QuoteProductConnector> findQuoteProductConnectors() throws DataAccessException;
	void saveQuoteProductConnector(QuoteProductConnector qpc) throws DataAccessException;
	
}
