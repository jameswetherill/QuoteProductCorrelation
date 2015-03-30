/**
 * 
 * Created : Mar 25, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.web
 */
package com.branded.holdings.qpc.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.branded.holdings.qpc.model.Quotes;
import com.branded.holdings.qpc.service.PhraseService;

/**
 * 
 */
@Controller
public class QuoteController {

	private PhraseService quoteService;
	
	/**
	 * 
	 */
	@Autowired
    public QuoteController(PhraseService quoteService) {
        this.quoteService = quoteService;
    }

	
	 @RequestMapping("/quotes")
	    public String showQuoteList(Map<String, Object> model) {
	        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects 
	        // so it is simpler for Object-Xml mapping
		 Quotes quotes = new Quotes();
		 quotes.getQuoteList().addAll(this.quoteService.findQuotes());
	        model.put("quotes", quotes);
	        return "quotes/quoteList";
	    }
}
