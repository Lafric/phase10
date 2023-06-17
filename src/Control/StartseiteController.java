package Control;

import Model.*;
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

/**
 * The StartseiteController handles the login process, including account creation,
 * password checking and so on.
 *
 * @author Billy Dongmo
 * @version 1.0
 */

public class StartseiteController {
    @FXML
    public Button registerButton;
    @FXML
    public Button anmeldeButton;
    @FXML
    public PasswordField passwort;
    @FXML
    public TextField nutzername;
    private DatabaseProvider databaseProvider;

    /**
     * to set up the LoginManager parameters such as DatabaseProvider and more
     *
     * @param databaseProvider is the database provider to use
     */
    public void setParams(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }


    public void AnmeldenGotoMenu(ActionEvent event) throws IOException {
        //perform something with server or Database zum Anmeldung/Verbindung

        //AuthResult loginres = performLogin(nutzername.getText(), passwort.getText());

        AuthResult loginres = performDummyLogin(nutzername.getText(), passwort.getText()); //zu löschen, wenn PerformLogin gut funktionniert
        //Todo: user credential stimmenn ?
        if(loginres.success){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/menu.fxml"));
            Parent root = loader.load();

            // TODO: Menu Controller params Übergeben

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
        FXMLLoader root = new FXMLLoader(getClass().getResource("/Graphics/registrieren.fxml"));
        Scene scene = new Scene(root.load());

        // set params
        RegistrierenController registrierenController = root.getController();
        registrierenController.setParams(this.databaseProvider);

        // next scene öffnen
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrieren");
        stage.show();
        Stage start = (Stage) registerButton.getScene().getWindow();
        start.close();
    }

    private AuthResult performLogin(String name, String pw) {
        System.out.println("Logging in..");
        if (this.databaseProvider.checkUser(name, pw)) {
            System.out.println("Valid name and password");
            return new AuthResult(true, "");
        } else {
            return new AuthResult(false, "Invalid name or password");
        }
    }



    // Todo: zum löschen, wenn PerformLogin gut funktionniert
    public AuthResult performDummyLogin(String name, String pw) {
        System.out.println("Logging in..");
        return new AuthResult(true, "");
    }
}
