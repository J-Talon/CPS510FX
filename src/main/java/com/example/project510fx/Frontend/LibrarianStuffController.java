package com.example.project510fx.Frontend;


import com.example.project510fx.Util.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LibrarianStuffController {

    @FXML
    private TextField queryTextField;

    @FXML
    private TextArea resultTextArea;

    private DatabaseConnection databaseConnection;

    @FXML
    private void initialize() {
        // Initialize the database model
        //databaseConnection= new databaseConnection();
    }

    @FXML
    private void executeQuery(ActionEvent event) {
        String query = queryTextField.getText();
     //   String result =databaseConnection.execute(query);
     //   resultTextArea.setText(result);
    }

    @FXML
    private void createTable(ActionEvent event) {
        String query = "YOUR CREATE TABLE SQL HERE";
    //    String result = databaseConnection.execute(query);
    //    resultTextArea.setText(result);
    }

    @FXML
    private void dropTable(ActionEvent event) {
        String query = "YOUR DROP TABLE SQL HERE";
   //     String result = databaseConnection.execute(query);
    //    resultTextArea.setText(result);
    }
}

