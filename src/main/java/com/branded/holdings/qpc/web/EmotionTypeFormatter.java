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
package com.branded.holdings.qpc.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.service.TopicService;

public class EmotionTypeFormatter implements Formatter<EmotionType> {
	 private final TopicService topicService;


	    @Autowired
	    public EmotionTypeFormatter(TopicService topicService) {
	        this.topicService = topicService;
	    }
   

    @Override
    public String print(EmotionType emotionType, Locale locale) {
        return emotionType.getName();
    }

	@Override
	public EmotionType parse(String text, Locale locale) throws ParseException {
		Collection<EmotionType> findEmotionTypes = this.topicService
				.findEmotionTypes();
		for (EmotionType type : findEmotionTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
