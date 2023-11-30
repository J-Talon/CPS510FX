package com.example.project510fx.Util;


import com.example.project510fx.Entities.*;

public class InsertionFormatter {



    public static String formatFeedbackInsert(Feedback feedback) {
        return "INSERT INTO FEEDBACK (FEEDBACKID, MEMID, LIBID, STARS, COMMENTS) VALUES " +
                "("+feedback.getFeedbackId()+", " +
                ""+ feedback.getMemberId()+", " +
                ""+feedback.getLibId()+", " +
                ""+feedback.getStars()+", " +
                "'"+feedback.getComment().replace("'", "''")+"')";
        //  '' is the escape character for ' in oracle sql
    }

    public static String formatLibrarianInsert(Librarian lib) {
        return "INSERT INTO LIBRARIAN (LIBID, ADMINKEY, NAME)  " +
                "VALUES ("+lib.getLibId()+", "+lib.getAdminKey()+", '"+lib.getName()+"')";
    }

    public static String formatMediaInsert(Media med) {
        return "INSERT INTO MEDIA (MEDIAID, AUTHOR, MEDIATITLE, PUBLISHDATE, MEDIATYPE, INSTOCK)  " +
                "VALUES ("+med.getId()+", " +
                "'"+med.getAuthor().replace("'", "''")+"', " +
                "'"+med.getTitle().replace("'", "''")+"', " +
                "to_date('"+med.getPublishDate()+"', 'RRRR-MM-DD'), " +
                "'"+med.getType()+"', "+med.getAvailability()+")";
    }

    public static String formatMemberInsert(Member mem) {
        return "INSERT INTO MEMBERS (MEMID, USERNAME, PASS, EMAIL, NAME, AMOUNTOWED)  " +
                "VALUES ("+mem.getMemId()+", " +
                "'"+mem.getUsername().replace("'", "''")+"', " +
                "'"+mem.getPassword().replace("'", "''")+"', " +
                "'"+mem.getEmail().replace("'", "''")+"', " +
                "'"+mem.getName().replace("'", "''")+"', " +
                ""+mem.getAmountOwed()+")";
    }

    public static String formatPenaltyInsert(Penalty penalty) {
        return "INSERT INTO PENALTIES (PENALTYID, MEMID, HISTORYID)  " +
                "VALUES ("+penalty.getPenaltyId()+", " +
                ""+penalty.getMemberId()+", " +
                ""+penalty.getTransactionId()+")";
    }

    public static String formatTransactionInsert(Transaction trans) {
        return "INSERT INTO TRANSACTIONDETAILS (HISTORYID, PICKUPDATE, EXPIREDATE, STATUS, MEMID, MEDIAID)  " +
                "VALUES ("+trans.getHistoryId()+", " +
                "to_date('"+trans.getPickupDate()+"', 'RRRR-MM-DD')," +
                " to_date('"+trans.getExpiryDate()+"', 'RRRR-MM-DD')," +
                " "+trans.getStatus()+", " +
                ""+trans.getMemberId()+", " +
                ""+trans.getMediaId()+")";
    }

}
