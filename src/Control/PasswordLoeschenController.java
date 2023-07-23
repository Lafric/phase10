package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

import Model.DatabaseProvider;
import Model.Identity;

public class PasswordLoeschenController {
    public Button button_betaetigen_acc_loeschen;
    public Button button_abbrechen_acc_loeschen;
    public PasswordField textfeld_passwort1;
    private Identity identity;
    private DatabaseProvider databaseProvider;

    public void setParams(Identity id, DatabaseProvider dbProvider) {
        this.identity = id;
        this.databaseProvider = dbProvider;
    }

    /** 
     * @param event
     * @throws IOException
     */
    public void accLoeschenOk(ActionEvent event) throws IOException {
        // Todo: close the Menu Stage
        // password stimmen
        String enteredPassword = textfeld_passwort1.getText();
        if (databaseProvider.checkUser(identity.getUsername(), enteredPassword)) {

            databaseProvider.deleteUser(identity.getUsername(), enteredPassword);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/startseite.fxml"));
            Parent root = loader.load();

            // TODO: Startseite Controller

            // next scene öffnen
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Startseite");

            StartseiteController startController = loader.getController();
            startController.setParams(databaseProvider);

            stage.setOnCloseRequest(e -> {
                // TODO: disconnect user
            });

            stage.show();
            Stage start = (Stage) button_betaetigen_acc_loeschen.getScene().getWindow();
            start.close();

            // close menu stage
            Stage menu = (Stage) button_betaetigen_acc_loeschen.getScene().getWindow();
            menu.close();
        } else {
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
