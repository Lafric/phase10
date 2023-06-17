package Control;

import Model.AuthResult;
import Model.DatabaseProvider;
import Model.Identity;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrierenController {
    public Button button_registrieren;
    public Button button_zurueck;
    public PasswordField passwort_2;
    public PasswordField passwort;
    public TextField nutzername;
    private DatabaseProvider databaseProvider;

    public void setParams(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public void RegisterUser(ActionEvent event) throws IOException {
        // 2 pwds stimmen ? gehe zu Menu
        if (!passwort.getText().equals("")) {
            if (passwort.getText().length() > 3 && !passwort.getText().contains(" ")) {
                if (passwort.getText().equals(passwort_2.getText())) {
                    // registrierung erfolgreich ? (keine Duplikate, keine Fehler)
                    AuthResult registrationres = performRegistration(nutzername.getText(), passwort.getText());
                    // passwort.getText());
                    if (registrationres.success) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/menu.fxml"));
                        Parent root = loader.load();

                        MenuController controller = loader.getController();
                        controller.changeNameLabel(new Identity(nutzername.getText()));

                        // Todo: Menu Controller

                        // next scene öffnen
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Menu");

                        stage.setOnCloseRequest(e -> {
                            // TODO: disconnect user
                        });

                        stage.show();
                        Stage start = (Stage) button_registrieren.getScene().getWindow();
                        start.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Name .");
                        alert.setHeaderText("Fehler beim Registrierung:\n" + registrationres.error);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fehler");
                    alert.setHeaderText("Die Passw\u00f6rter stimmen nicht \u00dcberein.");
                    alert.setContentText("Bitte erneut eingeben");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Name .");
                alert.setHeaderText("Ihr Passwort ist zu kurz.");
                alert.setContentText(
                        "Ihr Passwort muss mindestens 4 Zeichen enthalten, davon darf keines ein Leerzeichen sein!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Name .");
            alert.setHeaderText("kein Passwort wurde gesetzt");
            alert.setContentText("Bitte geben sie ein Passwort, mit mindestens 4 Zeichen");
            alert.showAndWait();
        }
        ;
    }

    public void ReturnToStartseite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/startseite.fxml"));
        Parent root = loader.load();

        // TODO: Startseite Controller

        // next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Startseite");

        stage.setOnCloseRequest(e -> {
            // TODO: disconnect user
        });

        stage.show();
        Stage start = (Stage) button_zurueck.getScene().getWindow();
        start.close();
    }

    public AuthResult performRegistration(String name, String pw) {
        System.out.println("Registrating..");
        if (this.databaseProvider.checkUser(name, pw)) {
            return new AuthResult(false, "User already exists");
        } else {
            this.databaseProvider.addUser(name, pw);
            return new AuthResult(true, "");
        }
    }
}
