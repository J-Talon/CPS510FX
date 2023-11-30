package com.example.project510fx;

import Util.Tuple3;
import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.DatabaseSystem.QueryMenu;
import com.example.project510fx.DatabaseSystem.TableMenu;

import com.example.project510fx.Entities.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {


    public static void printManual() {
        System.out.println("This is instructions");
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("LibrarianStuff.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Library System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch(args);
//
//        Scanner scanner = new Scanner(System.in);
//        try {
//
//            while (true) {
//
//                printManual();
//                String input = scanner.nextLine();
//                if (input.length() == 1) {
//                    processInput(input);
//                }
//                else {
//                    System.out.println("Invalid input, must be a single character.");
//                }
//            }
//        }
//        catch (Exception e) {
//           System.out.println("Error: "+e.getMessage());
//        }
//

    }

    public static void processInput(String s) {
        s = s.toLowerCase();
        char choice = s.charAt(0);

        switch (choice) {
            case 'a':
                TableMenu.createTables();
                break;

            case 'b':
                TableMenu.deleteTables();
                break;

            case 'c':
                TableMenu.populateTables();
                break;

            case 'd': {
                List<Transaction> transactions = QueryMenu.expiredJan20();
                if (transactions == null) {
                    throw new IllegalStateException("Unable to fetch transaction data");
                }
                for (Transaction t : transactions) {
                    t.display();
                }
            }
            break;

            case 'e':  //List<Tuple<Integer, Integer, String>>
            {
                List<Tuple3<Integer, Integer, String>> data = QueryMenu.goodReview();
                if (data == null) {
                    throw new IllegalStateException("Unable to fetch reviews data");
                }
                for (Tuple3<Integer, Integer, String> tup : data) {
                    tup.display();
                }
            }
            break;

            case 'f': {
                List<Tuple3<String, String, String>> data = QueryMenu.listEveryone();
                if (data == null) {
                    throw new IllegalStateException("Unable to fetch data on members");
                }

                for (Tuple3<?, ?, ?> tup : data) {
                    tup.display();
                }
            }
            break;

            case 'g': {
                Map<String, Integer> media = QueryMenu.mediaCount();
                if (media == null) {
                    throw new IllegalStateException("Unable to fetch data on media");
                }
                for (Map.Entry<String, Integer> e : media.entrySet()) {
                    System.out.println("Type: " + e.getKey() + " Amount:" + e.getValue());
                }
            }
            break;

            case 'h': {
                Map<Integer, String[]> map = QueryMenu.mediaInStock();
                if (map == null) {
                    throw new IllegalStateException("Unable to fetch data on media in stock");
                }

                //id title auth type
                for (Map.Entry<Integer, String[]> e : map.entrySet()) {
                    System.out.print("Id: "+e.getKey());
                    String[] array = e.getValue();
                    System.out.print(" Title: "+array[0]+" Author: "+array[1]+" Type: "+array[2]);
                    System.out.println();
                }

            }
            break;

            case 'i': {
                List<Util.Tuple4<Integer, String, String, Double>> owing = QueryMenu.owningMembers();
                if (owing == null) {
                    throw new IllegalStateException("Unable to fetch data on members with penalties");
                }

                for (Util.Tuple4<?,?,?,?> o : owing) {
                    o.display();
                }
            }
            break;

            case 'j':
                System.exit(0);
            break;



            default:
                System.out.println("Unknown input. ");
        }
    }
}
