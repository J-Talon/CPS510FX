package com.example.project510fx.Util.Queries;

import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.Entities.Media;
import com.example.project510fx.Entities.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FrontendParser {

    //memId, username, pass, email, name, owe
    public Member createMember(String username, String pass, String email, String name) throws Exception {
       int nextId = LibrarySystem.nextId("Members", "Memid");
       if (nextId == -1)
           throw new IllegalStateException("next id is invalid");

       return new Member(nextId,0,username,pass, email, name);
    }

    //mediaId, author, title, publish, type, instock
    public Media createMedia(String author, String title, String publish, String type, String instock) throws Exception {
        int nextId = LibrarySystem.nextId("Media", "MediaId");
        if (nextId == -1)
            throw new IllegalStateException("next id is invalid");

        int stock = Integer.parseInt(instock);
        if (stock != 0 && stock != 1) {
            throw new IllegalStateException("Stock must be 0 or 1");
        }
        return new Media(author,title,publish,nextId,stock,type);
    }


    public List<String> parse(String s) {
        List<String> strings = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
           strings.add(tokenizer.nextToken());
        }
        return strings;
    }
}
