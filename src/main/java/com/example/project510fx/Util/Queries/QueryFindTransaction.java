package com.example.project510fx.Util.Queries;

import com.example.project510fx.DatabaseSystem.QueryMenu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryFindTransaction extends QueryProcess {

    private int id, memId;


    @Override
    public void processQuery(ResultSet set) throws SQLException {
        id = memId = -1;

        if (set.next()) {
            id = set.getInt("HISTORYID");
            memId = set.getInt("MemId");
        }
    }

    public boolean compare(int transactionId, int memberId) {

        if (id == -1 || memId == -1) {
            throw new IllegalStateException("Member ids are invalid.");
        }

        return id == transactionId && memberId == memId;
    }
}
