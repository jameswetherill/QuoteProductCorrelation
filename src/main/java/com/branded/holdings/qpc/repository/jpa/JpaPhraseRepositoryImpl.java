/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.branded.holdings.qpc.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.branded.holdings.qpc.model.Product;
import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.QuoteProductConnector;
import com.branded.holdings.qpc.repository.PhraseRepository;

@Repository
public class JpaPhraseRepositoryImpl implements PhraseRepository {

    @PersistenceContext
    private EntityManager em;


	@Override
	@Cacheable(value = "quotes")
    @SuppressWarnings("unchecked")
    public Collection<Quote> findQuotes() {
		return this.em.createQuery("select distinct quote FROM Quote quote left join fetch quote.topics ORDER BY quote.author").getResultList();
	}


	@Override
	public void saveQuote(Quote quote) {
		
	}


	@Override
	@Cacheable(value = "products")
    @SuppressWarnings("unchecked")
    public Collection<Product> findProducts() {
		return this.em.createQuery("select distinct product FROM Product product left join fetch product.topics ORDER BY product.retailer").getResultList();	
	}


	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}


	@Override
	@Cacheable(value = "quote_product_connector")
    @SuppressWarnings("unchecked")
    public Collection<QuoteProductConnector> findQuoteProductConnectors() {
		return this.em.createQuery("select distinct qpc FROM QuoteProductConnector qpc left join fetch qpc.product left join fetch qpc.quote").getResultList();
	}


	@Override
	public void saveQuoteProductConnector(QuoteProductConnector connector) {
		// TODO Auto-generated method stub
		
	}

}
