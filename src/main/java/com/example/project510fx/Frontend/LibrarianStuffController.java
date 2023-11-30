package com.example.project510fx.Frontend;


import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.DatabaseSystem.QueryMenu;
import com.example.project510fx.DatabaseSystem.TableMenu;

import com.example.project510fx.Entities.Media;
import com.example.project510fx.Entities.Transaction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.util.List;
import java.util.Map;

public class LibrarianStuffController {

    @FXML
    private TextArea resultTextArea;
    @FXML
    private TextField keywords;
    @FXML

    LibrarySystem system;

    @FXML
    private void initialize() {
        system = LibrarySystem.getInstance();
        // Initialize the database model
        //databaseConnection= new databaseConnection();
    }

    @FXML
    private void executeQuery1(ActionEvent event) {
        try {
            List<Util.Tuple4<Integer, String, String, Double>> list = QueryMenu.owningMembers();


        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }

    }

    @FXML
    private void executeQuery2(ActionEvent event) {
        List<Util.Tuple3<String, String, String>> list = QueryMenu.listEveryone();
        resultTextArea.appendText("Q1");
}


@FXML
    private void executeQuery3(ActionEvent event) {

        try {
            List<Util.Tuple3<Integer, Integer, String>> list = QueryMenu.goodReview();

            if (list == null) {
                resultTextArea.setText("Error occurred");
              return;
            }

            resultTextArea.setText("");
            for (Util.Tuple3<?, ?, ?> t : list) {
                resultTextArea.appendText(t.toString() + "\n");
            }
        }
        catch (Exception e) {
            resultTextArea.appendText(e.getMessage());
        }


    }    @FXML
    private void executeQuery4(ActionEvent event) {
        try {
            List<Transaction> list = QueryMenu.expiredJan20();
            resultTextArea.setText("");
            for (Transaction t: list) {
                resultTextArea.appendText(t.toString() +"\n");
            }

        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }

    }


    @FXML
    private void executeQuery5(ActionEvent event) {

        resultTextArea.setText("");
        try {
            Map<String, Integer> map = QueryMenu.mediaCount();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String s = entry.getKey() +" "+ entry.getValue() +"\n";
                resultTextArea.appendText(s);
            }
        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }
    }


    @FXML
    private void executeQuery6(ActionEvent event) {

        try {
            Map<Integer, String[]> map = QueryMenu.mediaInStock();

            for (String[] array : map.values()) {

                StringBuilder b = new StringBuilder("");
                for (String s : array) {
                    b.append(s).append(" ");
                }

                resultTextArea.appendText(b.toString());
            }
        }
        catch (Exception e) {
            resultTextArea.setText("Error:"+e.getMessage());
        }
    }

    @FXML
    private void getMediaTitle(ActionEvent event) {
        String key = keywords.getText();
        Map<Integer, Media> media = system.getMediaByTitle(key);

        if (media == null) {
            resultTextArea.setText("Error occurred.");
            return;
        }

        resultTextArea.setText("");
        for (Media m: media.values()) {
            resultTextArea.appendText(m.toString()+"\n");
        }

    }
    @FXML
    private void createTable(ActionEvent event) {
        try {
            TableMenu.createTables();
            resultTextArea.setText("Tables created.");
        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }
    }


    @FXML
    private void dropTable(ActionEvent event) {
        try {
            TableMenu.deleteTables();
            resultTextArea.setText("Tables dropped.");
        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }
    }    @FXML
    private void populate(ActionEvent event) {
        try {
            TableMenu.populateTables();
            resultTextArea.setText("Tables populated.");
        }
        catch (Exception e) {
            resultTextArea.setText(e.getMessage());
        }
    }

        @FXML
    private void addAndRemoveData (ActionEvent event) {

    }
}

