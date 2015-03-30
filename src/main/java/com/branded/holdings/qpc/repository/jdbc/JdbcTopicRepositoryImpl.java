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
package com.branded.holdings.qpc.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.model.Pet;
import com.branded.holdings.qpc.model.Topic;
import com.branded.holdings.qpc.repository.TopicRepository;
import com.branded.holdings.qpc.util.EntityUtils;

/**
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcTopicRepositoryImpl implements TopicRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertTopic;


    @Autowired
    public JdbcTopicRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertTopic = new SimpleJdbcInsert(dataSource)
                .withTableName("topic")
                .usingGeneratedKeyColumns("id");

    }

    @Override
    public List<EmotionType> findEmotionTypes() throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM emotiontype ORDER BY id",
                params,
                BeanPropertyRowMapper.newInstance(EmotionType.class));
    }

    @Override
    public Topic findById(int id) throws DataAccessException {
        JdbcTopic topic;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            topic = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, description, emotiontype_id, weight FROM topic WHERE id=:id",
                    params,
                    new JdbcTopicRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Pet.class, new Integer(id));
        }
        topic.setType(EntityUtils.getById(findEmotionTypes(), EmotionType.class, topic.getTypeId()));

       
        return topic;
    }

    @Override
    public void save(Topic topic) throws DataAccessException {
        if (topic.isNew()) {
            Number newKey = this.insertTopic.executeAndReturnKey(
                    createTopicParameterSource(topic));
            topic.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE topic SET  type_id=:type_id, description=:description, weight=:weight" +
                            "WHERE id=:id",
                            createTopicParameterSource(topic));
        }
    }

    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Pet} instance.
     */
    private MapSqlParameterSource createTopicParameterSource(Topic topic) {
        return new MapSqlParameterSource()
                .addValue("id", topic.getId())
                .addValue("weight", topic.getWeight())
                .addValue("description", topic.getdescription())
                .addValue("type_id", topic.getType().getId());
    }

}
