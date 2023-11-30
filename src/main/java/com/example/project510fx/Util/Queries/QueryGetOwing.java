package com.example.project510fx.Util.Queries;



import com.example.project510fx.Entities.Format.FormatOwing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class QueryGetOwing extends QueryProcess {

    /*
    memid (i), username, mem email, amount owed (f)
     */

    List<FormatOwing> owing;

    public QueryGetOwing() {
        this.owing = new ArrayList<>();
    }

    public List<FormatOwing> QueryGetOwing() {
        return owing;
    }

    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            int id = set.getInt("Member Id");
            String username = set.getString("Username");
            String email = set.getString("Member Email");
            float owing = set.getFloat("Amount Owed");

            //ng(int memId, String username, String email, float owe) {
            FormatOwing entry = new FormatOwing(id, username, email, owing);
            this.owing.add(entry);
        }
    }


    public List<FormatOwing> getOwing() {
        return owing;
    }
}
