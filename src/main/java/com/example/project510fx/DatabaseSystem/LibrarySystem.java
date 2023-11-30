package com.example.project510fx.DatabaseSystem;

import com.example.project510fx.Entities.*;
import com.example.project510fx.Util.DatabaseConnection;
import com.example.project510fx.Util.InsertionFormatter;
import com.example.project510fx.Util.Queries.QueryFindLibrarian;
import com.example.project510fx.Util.Queries.QueryFindMember;
import com.example.project510fx.Util.Queries.QueryGetMedia;
import com.example.project510fx.Util.Queries.QueryId;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class LibrarySystem {


  /*
    This is a function which helps find the next id you should assign an entry if you are inserting.
    ids are always integers, if -1 is returned then there is an error and you should
     not use that id for inserting.
     */
    public int nextId(String tableName, String idName) {
        try {
            QueryId process = new QueryId(idName);
            DatabaseConnection.completeQuery("SELECT "+idName+" FROM "+tableName, process);
            return process.getCurrentId() + 1;
        }
        catch (Exception e) {
            return -1;
        }
    }

    /*
    Attempts to get a member object from the database. If not found or error, returns null
    This is used for when a member logs into the system. From the member object you can get info
    like the memberId, name, etc.
     */
    public Member getMemberAccount(String username, String password) {
        try {
            QueryFindMember process = new QueryFindMember();
            DatabaseConnection.completeQuery("SELECT * FROM Members WHERE username = " + username, process);
            Member member = process.getMember();
            if (!password.equals(member.getPassword()))
                return null;
            return member;

        }
        catch (Exception e) {
            return null;
        }
    }

    /*
    Gets all of the media in the media table, and returns it as a map<Media Id, Media object>

     */
    public Map<Integer, Media> getAllMedia() {
        try {
            QueryGetMedia process = new QueryGetMedia();
            DatabaseConnection.completeQuery("SELECT * FROM MEDIA", process);
            return process.getMedia();
        }
        catch (Exception e) {
            return null;
        }
   }


   /*
  This returns all of the media which has the specified id.
  there really only should be 1 media object in the map but in case something messes up you can
  get all of them.
    */
    public Map<Integer, Media> getMediaById(int id) {
        try {
            QueryGetMedia process = new QueryGetMedia();
            DatabaseConnection.completeQuery("SELECT * FROM MEDIA WHERE ID = "+id, process);
            return process.getMedia();
        }
        catch (Exception e) {
            return null;
        }
    }


    /*
    Returns a map of <Integer, Media object> for all of the media which has given title provided.
     */
    public Map<Integer, Media> getMediaByTitle(String title) {
        try {
            QueryGetMedia process = new QueryGetMedia();
            DatabaseConnection.completeQuery("SELECT * FROM MEDIA WHERE TITLE = "+title, process);
            return process.getMedia();
        }
        catch (Exception e) {
            return null;
        }
    }

    /*
    Inserts a review into the table, throws exception with err message if not successful
    insert into feedback (feedbackid, memid, libid, stars, comments) values (100, 2, 3, 4, 'a');
     */
    public void writeReview(String comment, int libId, int stars, Member member) {
        try {
            QueryFindLibrarian process = new QueryFindLibrarian();
            DatabaseConnection.completeQuery("SELECT * FROM LIBRARIAN WHERE LIBID = "+libId, process);
            Librarian lib = process.getLibrarian();
            if (lib == null) {
                throw new IllegalStateException("Could not find librarian");
            }

            int id = nextId("FEEDBACK", "feedbackid");
            if (id == -1) {
                throw new IllegalStateException("Could not find next id");
            }

            Feedback feedback = new Feedback(id, member.getMemId(), libId, comment, stars);
            String update = InsertionFormatter.formatFeedbackInsert(feedback);

            DatabaseConnection.completeUpdate(update);
        }
        catch (Exception e) {
            System.out.println("Unable to insert review");
        }
    }

    /*
    This generates a date, x days in the future from today's date,
    where x = dateOffset.
     */
    public Date generateFutureDate(int dateOffset) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDate currentDate = LocalDate.now();
        LocalDate pickup = currentDate.plusDays(dateOffset);
        return Date.from(pickup.atStartOfDay(zone).toInstant());
    }


    /*
    This inserts a new transaction object into the system, with the status "order Placed".
     */
    public void placeOrder(Member member, Media media, int pickupOffset, int expireOffset) {

        Date pickupDate = generateFutureDate(pickupOffset);
        Date expireDate = generateFutureDate(expireOffset);

        String pickup = new SimpleDateFormat("yyyy-MM-dd").format(pickupDate.getTime());
        String expire = new SimpleDateFormat("yyyy-MM-dd").format(expireDate.getTime());
        int nextId = nextId("TransactionDetails", "HistoryId");
        if (nextId == -1) {
            throw new IllegalStateException("Id is invalid");
        }

        Transaction trans = new Transaction(nextId,
                media.getId(),
                Transaction.Status.ORDER_PLACED.status(),
                member.getMemId(), pickup, expire);

       String update = InsertionFormatter.formatTransactionInsert(trans);
       DatabaseConnection.completeUpdate(update);
    }


    /*
    This adds a penalty object to the penalties table, and also updates the member's account to have an
    updated penalty amount they need to pay.
     */
    public void addPenaltyToMember(int memberId, int transactionId, double amount) {

        QueryFindMember processor = new QueryFindMember();
        String query = "SELECT * FROM Members WHERE MEMID = "+memberId;
        DatabaseConnection.completeQuery(query,processor);

        try {
           Member member = processor.getMember();
           double currentPenalty = member.getAmountOwed();
           currentPenalty += amount;

           String update = "Update Members SET AmountOwed = "+currentPenalty+" WHERE MemId = "+memberId;
           DatabaseConnection.completeUpdate(update);

           int id = nextId("Penalties","PenaltyId");
           if (id == -1) {
               throw new IllegalStateException("Could not get next id to insert");
           }
           Penalty penalty = new Penalty(id, memberId, transactionId);
           String insert = InsertionFormatter.formatPenaltyInsert(penalty);

           DatabaseConnection.completeUpdate(insert);

        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }


    /*
    this takes a member id an amount to pay.
    It subtracts the given amount from the member's penalty and returns the new amount they need to pay.
    If -1 is returned then an error has occurred and the amount owed has not changed.
     */
    public double payPenalty(int memberId, double amount) {
        try {
            QueryFindMember processor = new QueryFindMember();
            String query = "SELECT * FROM Members WHERE MEMID = "+memberId;
            DatabaseConnection.completeQuery(query,processor);

            Member member = processor.getMember();
            double owed = member.getAmountOwed();

            owed = Math.max(0, owed - amount);

            String update = "Update Members SET AmountOwed = "+owed+" WHERE MemId = "+memberId;
            DatabaseConnection.completeUpdate(update);
            return owed;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
