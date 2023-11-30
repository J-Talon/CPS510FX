package com.example.project510fx;

import Util.Tuple3;
import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.DatabaseSystem.QueryMenu;
import com.example.project510fx.DatabaseSystem.TableMenu;

import com.example.project510fx.Entities.Media;
import com.example.project510fx.Entities.Member;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Library System");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

       launch(args);

   //     Scanner scanner = new Scanner(System.in);
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


    }

    public static void processInput(String s) throws Exception {
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
            case 'd':
                LibrarySystem sys = LibrarySystem.getInstance();
                Member member = sys.getMemberAccount("a","b");
                sys.payPenalty(member.getMemId(),10);
                break;



            case 'j':
                System.exit(0);
            break;



            default:
                System.out.println("Unknown input. ");
        }
    }
}
