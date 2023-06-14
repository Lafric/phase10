package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Button botHinzufuegenButton;
    public Button botEntfernenButton;
    public Button raumErstellenButton;
    public Button raumLadenButton;
    public Button button_abmelden;
    public Button button_AccountDel;
    public ComboBox dropdwn_Rooms;
    public Button button_beitreten;
    public Label spieler1;
    public Label spieler2;
    public Label spieler3;
    public Label spieler4;
    public Label spieler5;
    public Label spieler6;
    public Button spiel_starten;
    public Button raumVerlassenButton;
    public TextField raumChat_eingabe;
    public TextArea raumChat;
    public TextField globalChat_eingabe;
    public TextArea globalChat_ausgabe;

    @FXML
    void showBestenliste(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("bestenliste.fxml"));
        Parent root = loader.load();

        //Todo: bestenliste Controller

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Bestenliste");
        stage.show();

    }

    public void delAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Account_löschen.fxml"));
        Parent root1 = loader.load();

        // Todo: controller of Account-löschen


        //next scene Password löschen
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("wollen Sie den Account wirklich l\u00f6schen?");
        stage.show();
    }

    public void abmelden(ActionEvent event) throws IOException {
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
        Stage start = (Stage) button_abmelden.getScene().getWindow();
        start.close();
    }
}
