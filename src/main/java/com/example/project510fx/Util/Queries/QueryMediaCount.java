package com.example.project510fx.Util.Queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryMediaCount extends QueryProcess {

    Map<String, Integer> mediaTypes;
    public QueryMediaCount() {

        mediaTypes = new HashMap<>();
    }

    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            String type = set.getString("MediaType");
            int count = set.getInt("TotalCount");
            mediaTypes.put(type, count);
        }
    }

    public Map<String, Integer> getMediaTypes() {
        return mediaTypes;
    }
}
