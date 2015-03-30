package com.branded.holdings.qpc.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class JdbcTopicRowMapper extends BeanPropertyRowMapper<JdbcTopic> {

	@Override
	public JdbcTopic mapRow(ResultSet rs, int rownum) throws SQLException {
		JdbcTopic topic = new JdbcTopic();
        topic.setId(rs.getInt("id"));
        topic.setDescription(rs.getString("description"));        
        topic.setWeight(rs.getDouble("weight"));
        topic.setTypeId(rs.getInt("emotiontype_id"));
        return topic;
	}

}
