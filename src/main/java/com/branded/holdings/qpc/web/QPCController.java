/**
 * 
 * Created : Mar 30, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.web
 */
package com.branded.holdings.qpc.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.branded.holdings.qpc.model.QuoteProductConnectors;
import com.branded.holdings.qpc.service.PhraseService;

/**
 * 
 */
@Controller
public class QPCController {

	private PhraseService qpcService;

	/**
		 * 
		 */
	@Autowired
	public QPCController(PhraseService qpcService) {
		this.qpcService = qpcService;
	}

	@RequestMapping("/qpcs")
	public String showProductList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a
		// collection of Vet objects
		// so it is simpler for Object-Xml mapping
		QuoteProductConnectors qpcsList = new QuoteProductConnectors();
		qpcsList.getQpcList().addAll(
				this.qpcService.findQuoteProductConnectors());
		model.put("qpcsList", qpcsList);
		return "qpcs/qpcList";
	}

}
