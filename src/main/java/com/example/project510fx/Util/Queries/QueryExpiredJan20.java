package com.example.project510fx.Util.Queries;
import com.example.project510fx.Entities.Transaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryExpiredJan20 extends QueryProcess {

    List<Transaction> transactionList;
    // HISTORYID PICKUPDAT EXPIREDAT     STATUS      MEMID    MEDIAID

    public QueryExpiredJan20() {
        transactionList = new ArrayList<>();
    }

    //(int historyId, int mediaId, int status, int memberId, String pickupDate, String expiryDate)
    @Override
    public void processQuery(ResultSet set) throws SQLException {
        while (set.next()) {
         int histId = set.getInt("HISTORYID");

         Date pickup = set.getDate("PICKUPDATE");
         Date expire = set.getDate("EXPIREDATE");

         //new SimpleDateFormat("yyyy-MM-dd").format(pickupDate.getTime()

            String pickupDate = new SimpleDateFormat("yyyy-MM-dd").format(pickup);
            String expiredate = new SimpleDateFormat("yyyy-MM-dd").format(expire);



         int status = set.getInt("STATUS");
         int memId = set.getInt("MEMID");
         int mediaId = set.getInt("MEDIAID");

         Transaction transaction = new Transaction(histId, mediaId, status, memId, pickupDate, expiredate);
         transactionList.add(transaction);
        }
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
