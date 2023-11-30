package com.example.project510fx.DatabaseSystem;


import com.example.project510fx.Util.DatabaseConnection;
import com.example.project510fx.Util.InsertionFormatter;
import com.example.project510fx.Util.ResourceManager;

import java.util.List;

public class TableMenu {

    public static void createTables() {
        try {
            ResourceManager manager = ResourceManager.getInstance();
            List<String> commands = manager.getTableCreateCommands();
            DatabaseConnection.completeManyUpdates(commands);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public static void deleteTables() {
     try {
         ResourceManager manager = ResourceManager.getInstance();
         List<String> commands = manager.getDropTableCommands();
         DatabaseConnection.completeManyUpdates(commands);
     }
     catch (Exception e) {
         System.out.println(e.getMessage());
     }
    }


    /*
media
member
lib
transaction
feedback
penalties
     */

    public static void populateTables() {
        try {
            ResourceManager manager = ResourceManager.getInstance();
            List<String> inserts = new java.util.ArrayList<>(manager.getMediaEntries().stream().map(InsertionFormatter::formatMediaInsert).toList());
            inserts.addAll(manager.getMemberEntries().stream().map(InsertionFormatter::formatMemberInsert).toList());
            inserts.addAll(manager.getLibrarianEntries().stream().map(InsertionFormatter::formatLibrarianInsert).toList());
            inserts.addAll(manager.getTransactionEntries().stream().map(InsertionFormatter::formatTransactionInsert).toList());
            inserts.addAll(manager.getFeedbackEntries().stream().map(InsertionFormatter::formatFeedbackInsert).toList());
            inserts.addAll(manager.getPenaltyEntries().stream().map(InsertionFormatter::formatPenaltyInsert).toList());

            DatabaseConnection.completeManyUpdates(inserts);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


//old code below
//============================
/*
    //Populating media
        int index = 0;
        try {

            System.out.println("Populating tables...");
            for (String query: TableQueries.MEDIA_TABLE_INSERTS) {
                System.out.println("Populating "+index+"...");
                DatabaseConnection.completeUpdate(query);
                index ++;
            }
            System.out.println("Populated table Media...");


            //Populating Member
            index = 0;
            for (String query: TableQueries.MEMBER_TABLE_INSERTS) {
                DatabaseConnection.completeUpdate(query);
                System.out.println("Populating "+index+"...");
                index ++;
            }
            System.out.println("Populated table Members...");


            //Populating Librarian
            index = 0;
            for (String query: TableQueries.LIBRARIAN_INSERTS) {
                DatabaseConnection.completeUpdate(query);
                System.out.println("Populating "+index+"...");
                index ++;
            }
            System.out.println("Populated table Librarian...");


            //Populating Transaction Details
            index = 0;
            for (String query: TableQueries.TRANSACTION_DETAIL_INSERTS) {
                DatabaseConnection.completeUpdate(query);
                System.out.println("Populating "+index+"...");
                index ++;

            }
            System.out.println("Populated table Transaction Details...");

            //Populating Feedback
            index = 0;
            for (String query: TableQueries.FEEDBACK_TABLE_INSERTS) {
                DatabaseConnection.completeUpdate(query);
                System.out.println("Populating "+index+"...");
                index ++;
            }
            System.out.println("Populated table Feedback...");


            //Populating Penalties
            index = 0;
            for (String query: TableQueries.PENALTY_INSERTS) {
                DatabaseConnection.completeUpdate(query);
                System.out.println("Populating "+index+"...");
                index ++;
            }
            System.out.println("Populated table Penalties...");

            System.out.println("Populated all tables.");
        }
        catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
 */