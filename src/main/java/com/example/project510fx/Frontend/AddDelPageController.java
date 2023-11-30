package com.example.project510fx.Frontend;


import com.example.project510fx.Entities.Member;
import com.example.project510fx.Util.Queries.*;


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
        Member mem = createMember(memID);
        mem =
}
    @FXML
    private void addMed(ActionEvent event) {

}
    @FXML
    private void delMed(ActionEvent event) {

}

}
