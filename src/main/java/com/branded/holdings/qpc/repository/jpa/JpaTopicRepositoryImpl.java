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
package com.branded.holdings.qpc.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.model.Topic;
import com.branded.holdings.qpc.repository.TopicRepository;

@Repository
public class JpaTopicRepositoryImpl implements TopicRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<EmotionType> findEmotionTypes() {
        return this.em.createQuery("SELECT ptype FROM EmotionType ptype ORDER BY ptype.name").getResultList();
    }

    @Override
    public Topic findById(int id) {
        return this.em.find(Topic.class, id);
    }

    @Override
    public void save(Topic topic) {
    	if (topic.getId() == null) {
    		this.em.persist(topic);     		
    	}
    	else {
    		this.em.merge(topic);    
    	}
    }

}
