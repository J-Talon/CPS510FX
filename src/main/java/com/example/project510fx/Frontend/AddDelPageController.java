package com.example.project510fx.Frontend;


import com.example.project510fx.DatabaseSystem.LibrarySystem;
import com.example.project510fx.Entities.Media;
import com.example.project510fx.Entities.Member;
import com.example.project510fx.Util.Queries.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

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
    private TextArea modifyResult;

    @FXML @SuppressWarnings("unused")
    private void addMem (ActionEvent event){
        LibrarySystem sys = LibrarySystem.getInstance();
        String memberInfo = memInfo.getText();

        List<String> values = FrontendParser.parse(memberInfo);

        try {
            Member mem = FrontendParser.createMember(values.get(0),values.get(1), values.get(2), values.get(3));
            sys.insertMember(mem);
            modifyResult.setText("Inserted Member");

        }
        catch (IndexOutOfBoundsException | IllegalStateException e) {
            modifyResult.setText("Insertion failed: "+e.getMessage());
        }

    }
    @FXML @SuppressWarnings("unused")
    private void delMem (ActionEvent event) {

        LibrarySystem sys = LibrarySystem.getInstance();
        String memIDText = memID.getText();
        try {
            int id = Integer.parseInt(memIDText);
            if (id < 0) {
                modifyResult.setText("Invalid member id");
                return;
            }

            boolean res = sys.deleteMember(id);

            if (res)
                modifyResult.setText("Deleted member");
            else
                modifyResult.setText("Failed to delete: could not find member with given id");
        }
        catch (NumberFormatException e) {
            modifyResult.setText("Invalid member id: "+e.getMessage());
        }
}
    @FXML @SuppressWarnings("unused")
    private void addMed(ActionEvent event) {
        LibrarySystem sys = LibrarySystem.getInstance();
        String info = medInfo.getText();

        List<String> values = FrontendParser.parse(info);

        try {
            Media media = FrontendParser.createMedia(values.get(0),values.get(1),
                    values.get(2), values.get(3),values.get(4));

            sys.insertMedia(media);
            modifyResult.setText("Inserted Media");

        }
        catch (IndexOutOfBoundsException | IllegalStateException e) {
            modifyResult.setText("Failed to insert: "+e.getMessage());
        }

   }
    @FXML @SuppressWarnings("unused")
    private void delMed(ActionEvent event) {
    LibrarySystem sys = LibrarySystem.getInstance();
    try {
        String info = medID.getText();
        int id = Integer.parseInt(info);
        if (id < 0) {
            modifyResult.setText("Invalid media id");
            return;
        }

       Media med = sys.getMediaById(id);
        if (med == null) {
            return;
        }

      boolean res = sys.deleteMedia(id);

        if (res)
         modifyResult.setText("Deleted media");
        else
            modifyResult.setText("Failed to delete:  could not find media with given id");

    }
    catch (Exception e) {
        modifyResult.setText("Failed to delete: "+e.getMessage());
    }


   }

}
