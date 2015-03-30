/**
 * 
 * Created : Mar 24, 2015
 * Creator : James Wetherill
 * QuoteProductCorrelation : com.branded.holdings.qpc.repository
 */
package com.branded.holdings.qpc.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.branded.holdings.qpc.model.BaseEntity;
import com.branded.holdings.qpc.model.EmotionType;
import com.branded.holdings.qpc.model.Topic;

/**
 * 
 */
public interface TopicRepository {
	/**
     * Retrieve all <code>EmotionType</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>EmotionType</code>s
     */
    List<EmotionType> findEmotionTypes() throws DataAccessException;

    /**
     * Retrieve a <code>topic</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>topic</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Topic findById(int id) throws DataAccessException;

    /**
     * Save a <code>topic</code> to the data store, either inserting or updating it.
     *
     * @param topic the <code>topic</code> to save
     * @see BaseEntity#isNew
     */
    void save(Topic topic) throws DataAccessException;
}
