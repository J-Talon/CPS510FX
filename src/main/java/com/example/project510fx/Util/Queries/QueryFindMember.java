package com.example.project510fx.Util.Queries;



import com.example.project510fx.Entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryFindMember extends QueryProcess {

    Member member;

    @Override
    public void processQuery(ResultSet set) throws SQLException {
        if (set.next()) {
            String username = set.getString("USERNAME");
            int id = set.getInt("MEMID");
            String pass = set.getString("PASS");
            String email = set.getString("EMAIL");
            double owed = set.getDouble("AMOUNTOWED");
            String name = set.getString("NAME");
            member = new Member(id, owed, username, pass, email, name);
        } else throw new IllegalStateException("Could not find member");

    }

    public Member getMember() {
        return member;
    }
}