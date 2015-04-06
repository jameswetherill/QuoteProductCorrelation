/**
 * 
 * Created : Apr 3, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.service
 */
package com.branded.holdings.qpc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.branded.holdings.qpc.model.Phrase;

/**
 * 
 */
@Service
public class PhraseProcessorImpl implements  PhraseProcessor, ApplicationContextAware {
	private String stoplists;
	@Autowired
	ApplicationContext ctx;
	
	/* (non-Javadoc)
	 * @see com.branded.holdings.qpc.service.PhraseProcessor#processPhrase(com.branded.holdings.qpc.model.Phrase)
	 */
	@Override
	public Phrase processPhrase(Phrase phrase) {
		return null;
	}

	/**
	 * @return the stoplists
	 */
	public String getStoplists() {
		return stoplists;
	}

	/**
	 * @param stoplists the stoplists to set
	 */
	public void setStoplists(String stoplists) {
		this.stoplists = stoplists;
		getStoplistText();
	}
	
	private void getStoplistText(){
		try {
			
			File in =ctx.getResource("classpath:"+stoplists).getFile();
			BufferedReader reader
			   = new BufferedReader(new FileReader(in));
			StringBuilder lines = new StringBuilder();
			String line = null;
            while ((line = reader.readLine()) != null) {
               lines.append(line);
            }
			reader.close();		
			stoplists = lines.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ctx= arg0;
		
	}


	
}
