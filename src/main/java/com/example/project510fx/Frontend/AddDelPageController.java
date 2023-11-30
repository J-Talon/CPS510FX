package com.example.project510fx.Frontend;


import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.DatabaseSystem.QueryMenu;
import com.example.project510fx.DatabaseSystem.TableMenu;
import com.example.project510fx.Entities.Librarian;
import com.example.project510fx.Entities.Transaction;
import com.example.project510fx.Util.DatabaseConnection;
import com.example.project510fx.DatabaseSystem.LibrarySystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDelPageController {

    @FXML
    private TextField memInfo;
    @FXML
    private TextField memID;
    @FXML
    private TextField medInfo;
    @FXML
    private TextField medID;
    @FXML
    private void addMem (ActionEvent event){
        String memberInfo = memInfo.getText();

    }
    @FXML
    private void delMem (ActionEvent event) {
        String memID = memInfo.getText();
}
    @FXML
    private void addMed(ActionEvent event) {

}
    @FXML
    private void delMed(ActionEvent event) {

}

}
