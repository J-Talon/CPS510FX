package com.example.project510fx.Util.Queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryMediaInStock extends QueryProcess {

    /*
    id title author type

     id - int
     */

    Map<Integer, String[]> queries;

    public QueryMediaInStock() {
        queries = new HashMap<>();
    }

    @Override
    public void processQuery(ResultSet set) throws SQLException {
     while (set.next()) {
         String[] array = new String[3];
         int id = set.getInt("id");
         array[0] = set.getString("Title");
         array[1] = set.getString("Author");
         array[2] = set.getString("Type");

         queries.put(id, array);
     }
    }

    public Map<Integer, String[]> getQueries() {
        return queries;
    }

}
