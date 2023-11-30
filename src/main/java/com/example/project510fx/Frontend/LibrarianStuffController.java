package com.example.project510fx.Frontend;


import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.DatabaseSystem.QueryMenu;
import com.example.project510fx.DatabaseSystem.TableMenu;
import com.example.project510fx.Entities.Transaction;
import com.example.project510fx.Util.DatabaseConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;



import java.util.List;
import java.util.Map;

public class LibrarianStuffController {

    @FXML
    private TextArea resultTextArea;
    LibrarySystem system;

    @FXML
    private void initialize() {
        system = LibrarySystem.getInstance();
        // Initialize the database model
        //databaseConnection= new databaseConnection();
    }

    @FXML
    private void executeQuery1(ActionEvent event) {
        List<Util.Tuple4<Integer, String, String, Double >> list = QueryMenu.owningMembers();
        resultTextArea.appendText("Q1");

    }    @FXML
    private void executeQuery2(ActionEvent event) {
        List<Util.Tuple3<String, String, String>> list = QueryMenu.listEveryone();
        resultTextArea.appendText("Q1");
}    @FXML
    private void executeQuery3(ActionEvent event) {
        List<Util.Tuple3<Integer, Integer, String>> list = QueryMenu.goodReview();
        for (Util.Tuple3 t: list) {
            resultTextArea.appendText(t.toString()+"\n");
        }

    }    @FXML
    private void executeQuery4(ActionEvent event) {
        List<Transaction> list = QueryMenu.expiredJan20();
        resultTextArea.appendText("Q1");
    }    @FXML
    private void executeQuery5(ActionEvent event) {
        Map<String, Integer> map = QueryMenu.mediaCount();
        resultTextArea.appendText("Q1");
    }    @FXML
    private void executeQuery6(ActionEvent event) {
        Map<Integer, String[]> map = QueryMenu.mediaInStock();
        resultTextArea.appendText("Q1");
    }
    @FXML
    private void createTable(ActionEvent event) {
        TableMenu.createTables();
    }


    @FXML
    private void dropTable(ActionEvent event) {
        TableMenu.deleteTables();
        System.out.println("Done");
    }    @FXML
    private void populate(ActionEvent event) {
        TableMenu.populateTables();
        System.out.println("Done");
    }
}

