/**
 * 
 * Created : Mar 30, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.model
 */
package com.branded.holdings.qpc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 */
public class QuoteProductConnectors {
	private List<QuoteProductConnector> qpcList;

    @XmlElement
    public List<QuoteProductConnector> getQpcList() {
        if (qpcList == null) {
        	qpcList = new ArrayList<QuoteProductConnector>();
        }
        return qpcList;
    }
}
