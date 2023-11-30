package com.example.project510fx.Util;



import com.example.project510fx.Util.Queries.QueryProcess;

import java.sql.*;
import java.util.List;


public class DatabaseConnection {
    static final String dbURL1 =   "jdbc:oracle:thin:t6jiang/02175811@oracle.scs.ryerson.ca:1521:orcl";


    /*
    Use this with single select statements ONLY
    this function takes in a query (E.g select * from transactiondetails)
    and a QueryProcess object which handles the result of the query's ResultSet object.

    To perform your own query you should create a subclass which inherits class QueryProcess,
    and fill out the processQuery(ResultSet) function to suit your needs.

    if there is an error performing the operation an illegalStateException is thrown.

    Example:
     MyQuery query = new MyQuery();
     DatabaseConnection.completeQuery("Select * from members", query);
     ...

     */
    public static void completeQuery(String query, QueryProcess result) throws IllegalStateException {
        Connection connection = null;
        Statement stmt = null;

     try {
         connection = DriverManager.getConnection(dbURL1);


         if (connection == null)
             throw new IllegalStateException("Could not create connection");

         stmt = connection.createStatement();

         ResultSet rs = stmt.executeQuery(query);

         if (result != null)
             result.processQuery(rs);
     }
     catch (Exception e) {
         throw new IllegalStateException("Error completing query: "+e.getMessage());
     }
     finally {
         try {
             if (stmt != null)
                 stmt.close();
         }
         catch (Exception ignored) {

         }
         try {
             if (connection != null)
                 connection.close();
         }
         catch (Exception ignored) {

         }
     }
    }



    /*
    Use this with single Delete/Insert/create statements ONLY
    This function takes a query (Insert/Delete/Create table) which updates the table
    in some way. This can be for creating a table, adding a new entry, etc.

    If there is an error completing the request, an IllegalStateException is thrown with an error message.
     */
    public static void completeUpdate(String query) throws IllegalStateException {


        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection(dbURL1);
            if (connection == null)
                throw new IllegalStateException("Could not create connection");

           stmt = connection.createStatement();

            stmt.executeUpdate(query);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error completing update: "+e.getMessage());
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            }
            catch (Exception ignored) {

            }
            try {
                if (connection != null)
                    connection.close();
            }
            catch (Exception ignored) {

            }

        }
    }


    public static void completeManyUpdates(List<String> queries) throws IllegalStateException {
        Connection connection = null;
        Statement statement = null;
        String currentStatement = null;

        try {
            connection = DriverManager.getConnection(dbURL1);

            if (connection == null)
                throw new IllegalStateException("Could not create connection");

            statement = connection.createStatement();

                for (String s : queries) {
                    currentStatement = s;
                    try {
                        statement.executeUpdate(s);
                     }
                    catch (SQLException e) {
                        System.out.println("Could not complete update for a given table: " +
                                ""+e.getMessage()+": "+currentStatement);
                    }
                }

        }
        catch (Exception e) {
            throw new IllegalStateException("Error completing updates: "+e.getMessage() +"\n" + currentStatement);
        }
        finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception ignored) {

            }

          try {
              if (connection != null) {
                  connection.close();
              }
          }
          catch (Exception ignored) {

          }

        }

    }
}
