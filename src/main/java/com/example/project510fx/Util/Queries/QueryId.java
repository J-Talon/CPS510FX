package com.example.project510fx.Util.Queries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryId extends QueryProcess {

    int currentId = 0;
    final String idName;

    public QueryId(String idName) {
        this.idName = idName;
    }
    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            int id = set.getInt(idName);
            currentId = Math.max(currentId, id);
        }
    }


    public int getCurrentId() {
        return currentId;
    }
}
