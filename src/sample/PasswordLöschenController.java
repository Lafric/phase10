package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class PasswordLöschenController {
    public Button button_betaetigen_acc_loeschen;
    public Button button_abbrechen_acc_loeschen;
    public PasswordField textfeld_passwort1;

    public void accLoeschenOk(ActionEvent event) throws IOException {
        // Todo: close the Menu Stage
        // password stimmen
        if(textfeld_passwort1.getText().equals("dump")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startseite.fxml"));
            Parent root = loader.load();

            //TODO: Startseite Controller

            //next scene öffnen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Startseite");

            stage.setOnCloseRequest(e -> {
                // TODO: disconnect user
            });

            stage.show();
            Stage start = (Stage) button_betaetigen_acc_loeschen.getScene().getWindow();
            start.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText("Passwort ist falsch.");
            alert.setContentText("Please Try again");
            alert.showAndWait();
        }
    }

    public void accLoeschenNo(ActionEvent event) {
        Stage start = (Stage) button_abbrechen_acc_loeschen.getScene().getWindow();
        start.close();
        return;
    }
}
