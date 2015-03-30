/**
 * 
 * Created : Mar 25, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.model
 */
package com.branded.holdings.qpc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 */
@XmlRootElement
public class Quotes {

	private List<Quote> quotes;

    @XmlElement
    public List<Quote> getQuoteList() {
        if (quotes == null) {
        	quotes = new ArrayList<Quote>();
        }
        return quotes;
    }

}
