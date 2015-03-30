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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.model.Topic;
import com.branded.holdings.qpc.repository.TopicRepository;

/**
 * Mostly used as a facade for all Topic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * 
 */
@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<EmotionType> findEmotionTypes() throws DataAccessException {
        return topicRepository.findEmotionTypes();
    }


    @Override
    @Transactional(readOnly = true)
    public Topic findTopicById(int id) throws DataAccessException {
        return topicRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveTopic(Topic pet) throws DataAccessException {
    	topicRepository.save(pet);
    }

}
