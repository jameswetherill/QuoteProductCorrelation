/**
 * 
 * Created : Mar 30, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.repository.jdbc
 */
package com.branded.holdings.qpc.repository.jdbc;

import com.branded.holdings.qpc.model.QuoteProductConnector;

/**
 * 
 */
public class JbcQuoteProductConnector extends QuoteProductConnector {
	private int quoteId;

    private int productId;


    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public int getQuoteId() {
        return this.quoteId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return this.productId;
    }
}
