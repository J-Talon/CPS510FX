package Frontend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Button button_signin;

    public void initialize() {
        button_signin.setOnAction(event -> {
            String username = tf_username.getText();
            String password = tf_password.getText();

            try {
                if (authenticateUser(username, password)) {
                    Stage stage = (Stage) button_signin.getScene().getWindow();
                    stage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/LibrarianStuff.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Login Failed");
                    alert.setContentText("Invalid username or password. Please try again.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        String url = "";
        String user = "root";
        String pass = "";

        try {
            Class.forName("");

            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    return true;
                }
            }

            conn.close();
            stmt.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}