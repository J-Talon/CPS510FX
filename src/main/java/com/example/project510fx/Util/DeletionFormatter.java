package com.example.project510fx.Util;

import com.example.project510fx.Entities.*;

public class DeletionFormatter {
    //delete from Penalties where penaltyId = 1;

    private static String getString(String tableName, String idName, int id) {
        return "delete from "+tableName+" where "+idName+" = "+id;
    }

    public static String formatMediaDel(Media med) {
        return getString("Media", "MediaId", med.getId());
    }

    public static String formatMemberDel(Member mem) {
        return getString("Members", "MemId", mem.getMemId());
    }
}
