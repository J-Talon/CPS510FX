package com.example.project510fx.Util.Queries;


import com.example.project510fx.Util.Tuple4;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class QueryGetOwing extends QueryProcess {

    /*
        int memId;
    String username, email;
    float owe;

     */

    List<Tuple4<Integer, String, String, Double>> owing;

    public QueryGetOwing() {
        this.owing = new ArrayList<>();
    }

    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            int id = set.getInt("Member Id");
            String username = set.getString("Username");
            String email = set.getString("Member Email");
            Double owing = set.getDouble("Amount Owed");

            //ng(int memId, String username, String email, float owe) {
            Tuple4<Integer, String,String,Double> tup = new Tuple4<>(id, username, email, owing);
            this.owing.add(tup);
        }
    }


    public List<Tuple4<Integer, String,String,Double>> getOwing() {
        return owing;
    }
}
