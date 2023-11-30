package com.example.project510fx.Util.Queries;


import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class QueryProcess {
    public abstract void processQuery(ResultSet set) throws SQLException;

}
