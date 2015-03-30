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
package com.branded.holdings.qpc.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.model.Topic;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface TopicService {

	Collection<EmotionType> findEmotionTypes() throws DataAccessException;

	Topic findTopicById(int id) throws DataAccessException;

	void saveTopic(Topic topic) throws DataAccessException;

}