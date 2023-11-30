package com.example.project510fx.DatabaseSystem;


import Util.Tuple3;

import com.example.project510fx.Entities.Transaction;
import com.example.project510fx.Util.DatabaseConnection;
import com.example.project510fx.Util.Queries.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class QueryMenu {



// HISTORYID PICKUPDAT EXPIREDAT     STATUS      MEMID    MEDIAID
    public static List<Transaction> expiredJan20() {

            QueryExpiredJan20 process = new QueryExpiredJan20();
            String query = "SELECT * From transactionDetails Where expiredate > TO_DATE('2023-01-20','RRRR-MM-DD') ORDER by ExpireDate Desc";
            DatabaseConnection.completeQuery(query, process);
            return process.getTransactionList();
    }


    ///Libid, stars, comments
    @Nullable
    public static List<Tuple3<Integer, Integer, String>> goodReview () {
        try {
           QueryGoodReviews process =  new QueryGoodReviews();
            String query = "SELECT libid, stars, comments FROM feedback WHERE stars >= (SELECT AVG(stars) FROM feedback) ORDER BY stars DESC" ;
            DatabaseConnection.completeQuery(query, process);
            return process.getFeedbackList();
        }
        catch (Exception e) {
            throw new IllegalStateException("Error fetching data: "+e.getMessage());
        }

    }

    public static List<Tuple3<String, String, String>> listEveryone () {
        try {
            QueryListEveryone process = new QueryListEveryone();
            String query = "SELECT DISTINCT Name AS PersonnelName, Email AS ContactInfo, 'Member' AS PersonnelType FROM Members" + " UNION " + "SELECT DISTINCT Name AS PersonnelName, NULL AS ContactInfo, 'Librarian' AS PersonnelType FROM Librarian";
            DatabaseConnection.completeQuery(query, process);
            return process.getPersonnelList();
        }
        catch (Exception e) {
            System.out.println("Error fetching data");
            return null;

        }

    }

    public static Map<String, Integer> mediaCount() throws Exception {

            QueryMediaCount process = new QueryMediaCount();
            String query = "SELECT MEDIATYPE, COUNT(*) AS TotalCount FROM MEDIA GROUP BY MEDIATYPE";
            DatabaseConnection.completeQuery(query, process);
            return process.getMediaTypes();

    }


    public static Map<Integer, String[]> mediaInStock () throws Exception {

           QueryMediaInStock  process = new QueryMediaInStock();
            String query = "SELECT mediaid AS \"Id\"," + "   mediatitle AS \"Title\"," + "   author AS \"Author\"," + "   mediatype AS \"Type\"" + "   FROM media WHERE media.instock = 1 ORDER BY mediatitle ASC";
            DatabaseConnection.completeQuery(query, process);
            return process.getQueries();
    }

    public static List<Util.Tuple4<Integer, String, String, Double>> owningMembers() {

            QueryGetOwing process = new QueryGetOwing();
            String query = "SELECT DISTINCT members.memid AS \"Member id\", " +
                    "members.username AS \"Username\", " +
                    "members.email AS \"Member email\", " +
                    "members.amountowed AS \"Amount Owed\" " +
                    "FROM members INNER JOIN penalties ON members.memid = penalties.memid " +
                    "WHERE members.amountowed > 10 ORDER BY amountowed ASC";
            DatabaseConnection.completeQuery(query, process);
            return process.getOwing() ;

    }
}
