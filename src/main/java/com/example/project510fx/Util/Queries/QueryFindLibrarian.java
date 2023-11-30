package com.example.project510fx.Util.Queries;


import com.example.project510fx.Entities.Librarian;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryFindLibrarian extends QueryProcess {


    Librarian librarian;
    @Override
    public void processQuery(ResultSet set) throws SQLException {

        if (set.next()) {
            //find the librarian
            //if it exists then get it



        }
        librarian = null;
    }


    public Librarian getLibrarian(){
        return librarian;
    }
}
