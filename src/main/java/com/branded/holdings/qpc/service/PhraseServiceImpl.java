/**
 * 
 * Created : Mar 26, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.service
 */
package com.branded.holdings.qpc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.branded.holdings.qpc.model.Product;
import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.QuoteProductConnector;
import com.branded.holdings.qpc.repository.PhraseRepository;
import com.branded.holdings.qpc.repository.TopicRepository;

/**
 * 
 */
@Service
public class PhraseServiceImpl implements PhraseService {

	@Autowired
	private TopicRepository topicRepo;

	@Autowired
	private PhraseRepository phraseRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.branded.holdings.qpc.service.PhraseService#findQuotes()
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<Quote> findQuotes() throws DataAccessException {
		return phraseRepo.findQuotes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.branded.holdings.qpc.service.PhraseService#saveQuote(com.branded.
	 * holdings.qpc.model.Quote)
	 */
	@Override
	public void saveQuote(Quote quote) throws DataAccessException {

		phraseRepo.saveQuote(quote);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.branded.holdings.qpc.service.PhraseService#processQuote(com.branded
	 * .holdings.qpc.model.Quote)
	 */
	@Override
	public void processQuote(Quote quote) {
		// TODO
	}

	@Override
	public Collection<Product> findProducts() throws DataAccessException {
		return phraseRepo.findProducts();
	}

	@Override
	public void saveProduct(Product product) throws DataAccessException {
		phraseRepo.saveProduct(product);
	}

	@Override
	public void processProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<QuoteProductConnector> findQuoteProductConnectors()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return phraseRepo.findQuoteProductConnectors();
	}

	@Override
	public void saveQuoteProductConnector(QuoteProductConnector qpc)
			throws DataAccessException {
		phraseRepo.saveQuoteProductConnector(qpc);
		
	}

}
