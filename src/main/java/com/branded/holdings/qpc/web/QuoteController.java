/**
 * 
 * Created : Mar 25, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.web
 */
package com.branded.holdings.qpc.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.Quotes;
import com.branded.holdings.qpc.service.PhraseService;

/**
 * 
 */
@Controller
public class QuoteController {

	private PhraseService quoteService;
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
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
	 
	 @RequestMapping(value = "/quotes/new", method = RequestMethod.GET)
	    public String initCreationForm(Map<String, Object> model) {
		 Quote quote = new Quote();
	        model.put("quote", quote);
	        return "process/createOrUpdateQuoteForm";
	    }
	 
	 @RequestMapping(value = "/quotes/new", method = RequestMethod.POST)
	    public String processCreationForm(@Valid Quote quote, BindingResult result, SessionStatus status) {
	        if (result.hasErrors()) {
	            return "process/createOrUpdateQuoteForm";
	        } else {
	            this.quoteService.processQuote(quote);
	            status.setComplete();
	            return "redirect:/process/" + quote.getId();
	        }
	    }
}
