package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StartseiteController {
    @FXML
    public TextField servername;
    @FXML
    public Button registerButton;
    @FXML
    public Button anmeldeButton;
    @FXML
    public PasswordField passwort;
    @FXML
    public TextField nutzername;


    public void AnmeldenGotoMenu(ActionEvent event) throws IOException {
        // TODO: perform something with server or Database zum Anmeldung/Verbindung

        //Todo: user cresdential stimmenn ?
        if(true){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            // TODO: Menu Controller

            //next scene öffnen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");

            stage.setOnCloseRequest(e -> {
                // TODO: disconnect user
            });

            stage.show();
            Stage start = (Stage) anmeldeButton.getScene().getWindow();
            start.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText("Nutzername oder Passwort falsch.");
            alert.setContentText("Please Try again");
            alert.showAndWait();
        }
    }

    public void gotoRegister(ActionEvent event) throws IOException {
        // TODO: perform something with server or Database zum Anmeldung/Verbindung

        //load registrieren
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrieren.fxml"));
        Parent root = loader.load();
          // TODO: Registrieren Controller
        // next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registrieren");
        stage.show();
        Stage start = (Stage) registerButton.getScene().getWindow();
        start.close();
    }
}
