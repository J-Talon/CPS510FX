package com.example.project510fx.Util.Queries;

import Util.Tuple3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryListEveryone extends QueryProcess {

    List<Tuple3<String, String,String>> personnelList;

    public QueryListEveryone() {
        personnelList = new ArrayList<>();
    }

    //personnelname contactinfo personneltype
    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            String name = set.getString("PersonnelName");
            String contact = set.getString("ContactInfo");
            String type = set.getString("PersonnelType");

            personnelList.add(new Tuple3<>(name, contact, type));


        }
    }
    
    public List<Tuple3<String, String, String>> getPersonnelList() {
        return personnelList;
    }
}
