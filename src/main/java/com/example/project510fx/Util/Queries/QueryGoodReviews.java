package com.example.project510fx.Util.Queries;

import Util.Tuple3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryGoodReviews extends QueryProcess {

    //Libid, stars, comments
    List<Tuple3<Integer, Integer, String>> feedbackList;

    public QueryGoodReviews() {
        feedbackList = new ArrayList<>();

    }

    //k(int feedbackId, int memberId, int libId, String comment) {

    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
            int libId = set.getInt("LibId");
            int stars = set.getInt("Stars");
            String comment = set.getString("Comments");

            //(int feedbackId, int memberId, int libId, String comment, int stars
            feedbackList.add(new Tuple3<>(libId, stars, comment));



        }
    }

    public List<Tuple3<Integer, Integer, String>> getFeedbackList() {
     return feedbackList;
    }
}
