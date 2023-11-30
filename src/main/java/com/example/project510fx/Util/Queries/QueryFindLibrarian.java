package com.example.project510fx.Util.Queries;


import com.example.project510fx.Entities.Librarian;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryFindLibrarian extends QueryProcess {


    Librarian librarian = null;
    @Override
    public void processQuery(ResultSet set) throws SQLException {

        if (set.next()) {
            int id = set.getInt("LibId");
           int key =  set.getInt("Adminkey");
            String name = set.getString("Name");

            librarian = new Librarian(id, key, name);
        }

    }


    public Librarian getLibrarian(){
        return librarian;
    }
}
