package com.branded.holdings.qpc.repository.jdbc;

import com.branded.holdings.qpc.model.Topic;

public class JdbcTopic extends Topic {
	private int typeId;

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return this.typeId;
    }

}
