package com.example.project510fx.Util.Queries;



import com.example.project510fx.Entities.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QueryGetMedia extends QueryProcess {

    Map<Integer, Media> media;

    public QueryGetMedia() {
        media = new HashMap<>();
    }
    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            int id = set.getInt("MEDIAID");
            String title = set.getString("MEDIATITLE");
            String auth = set.getString("AUTHOR");
            String publish = set.getString("PUBLISHDATE");
            String type = set.getString("MEDIATYPE");
            int instock = set.getInt("INSTOCK");

            Media med = new Media(auth, title, publish,id, instock,type);
            media.put(id, med);
        }
    }

    public Map<Integer, Media> getMedia() {
        return media;
    }
}
