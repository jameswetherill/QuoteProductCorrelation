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

import com.branded.holdings.qpc.model.Products;
import com.branded.holdings.qpc.service.PhraseService;

/**
 * 
 */
@Controller
public class ProductController {

	private PhraseService productService;
	
	/**
	 * 
	 */
	@Autowired
    public ProductController(PhraseService productService) {
        this.productService = productService;
    }

	
	 @RequestMapping("/products")
	    public String showProductList(Map<String, Object> model) {
	        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects 
	        // so it is simpler for Object-Xml mapping
		 Products products = new Products();
		 products.getProductList().addAll(this.productService.findProducts());
	        model.put("products", products);
	        return "products/productList";
	    }
}
