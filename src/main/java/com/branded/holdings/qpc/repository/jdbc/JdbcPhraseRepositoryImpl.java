package com.branded.holdings.qpc.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.branded.holdings.qpc.model.Pet;
import com.branded.holdings.qpc.model.Product;
import com.branded.holdings.qpc.model.Quote;
import com.branded.holdings.qpc.model.QuoteProductConnector;
import com.branded.holdings.qpc.model.Topic;
import com.branded.holdings.qpc.repository.PhraseRepository;
import com.branded.holdings.qpc.util.EntityUtils;

@Repository
public class JdbcPhraseRepositoryImpl implements PhraseRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertQuote;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcPhraseRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        
        this.insertQuote = new SimpleJdbcInsert(dataSource)
        .withTableName("quote")
        .usingGeneratedKeyColumns("id");
        
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Refresh the cache of Quotes that the ClinicService is holding.
     *
     * @see com.branded.holdings.qpc.model.service.ClinicService#shouldFindQuotes()
     */
    @Override
    public Collection<Quote> findQuotes() throws DataAccessException {
        List<Quote> quotes = new ArrayList<Quote>();
        // Retrieve the list of all quotes.
        quotes.addAll(this.jdbcTemplate.query(
                "SELECT id, description, author FROM quote ORDER BY author",
                BeanPropertyRowMapper.newInstance(Quote.class)));

        // Retrieve the list of all possible specialties.
        final List<Topic> topics = this.jdbcTemplate.query(
                "SELECT id, name FROM topic",
                BeanPropertyRowMapper.newInstance(Topic.class));

        // Build each vet's list of specialties.
        for (Quote quote : quotes) {
            final List<Integer> topicIds = this.jdbcTemplate.query(
                    "SELECT topic_id FROM quote_topic WHERE quote_id=?",
                    new BeanPropertyRowMapper<Integer>() {
                        @Override
                        public Integer mapRow(ResultSet rs, int row) throws SQLException {
                            return Integer.valueOf(rs.getInt(1));
                        }
                    },
                    quote.getId().intValue());
            for (int topicId : topicIds) {
                Topic topic = EntityUtils.getById(topics, Topic.class, topicId);
                quote.addTopic(topic);
            }
        }
        return quotes;
    }

	

	@Override
	public void saveQuote(Quote quote) {
		if (quote.isNew()) {
            Number newKey = this.insertQuote.executeAndReturnKey(
                    createQuoteParameterSource(quote));
            quote.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE quote SET description=:description, author=:author " +
                            "WHERE id=:id",
                    createQuoteParameterSource(quote));
        }
		
	}
	
	/**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Pet} instance.
     */
    private MapSqlParameterSource createQuoteParameterSource(Quote quote) {
        return new MapSqlParameterSource()
                .addValue("id", quote.getId())
                .addValue("description", quote.getDescription())
                .addValue("author", quote.getAuthor());
    }

	@Override
	public Collection<Product> findProducts() {
		 List<Product> products = new ArrayList<Product>();
	        // Retrieve the list of all quotes.
	        products.addAll(this.jdbcTemplate.query(
	                "SELECT id, description, author FROM quote ORDER BY author",
	                BeanPropertyRowMapper.newInstance(Product.class)));

	        // Retrieve the list of all possible specialties.
	        final List<Topic> topics = this.jdbcTemplate.query(
	                "SELECT id, name FROM topic",
	                BeanPropertyRowMapper.newInstance(Topic.class));

	        // Build each vet's list of specialties.
	        for (Product product : products) {
	            final List<Integer> topicIds = this.jdbcTemplate.query(
	                    "SELECT topic_id FROM product_topic WHERE product_id=?",
	                    new BeanPropertyRowMapper<Integer>() {
	                        @Override
	                        public Integer mapRow(ResultSet rs, int row) throws SQLException {
	                            return Integer.valueOf(rs.getInt(1));
	                        }
	                    },
	                    product.getId().intValue());
	            for (int topicId : topicIds) {
	                Topic topic = EntityUtils.getById(topics, Topic.class, topicId);
	                product.addTopic(topic);
	            }
	        }
	        return products;
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<QuoteProductConnector> findQuoteProductConnectors() {
		 List<QuoteProductConnector> qpcs = new ArrayList<QuoteProductConnector>();
	        // Retrieve the list of all quotes.
		 qpcs.addAll(this.jdbcTemplate.query(
	                "SELECT id, quote_id, product_id FROM QuoteProductConnector",
	                BeanPropertyRowMapper.newInstance(QuoteProductConnector.class)));

	        
		return qpcs;
	}

	@Override
	public void saveQuoteProductConnector(QuoteProductConnector connector) {
		// TODO Auto-generated method stub
		
	}
}
