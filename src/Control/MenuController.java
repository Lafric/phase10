package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import Communication.ChatRefresh;
import Communication.Message;
import Communication.MessageService;
import Communication.RMIServer;
import Model.DatabaseProvider;
import Model.Identity;

public class MenuController {
    public Button botHinzufuegenButton;
    public Button botEntfernenButton;
    public Button raumErstellenButton;
    public Button raumLadenButton;
    public Button button_abmelden;
    public Button button_AccountDel;
    public ComboBox dropdwn_Rooms;
    public Button button_beitreten;
    public Label nameLabel;
    public Label chatLabel;
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
    public Identity identity;

    private MessageService messageService;

    public MenuController(MessageService messageService) {
        this.messageService = messageService;
    }

    public MenuController() {
        this.messageService = new MessageService();
    }

    @FXML
    public void initialize() {
        Runnable refresh = new ChatRefresh(globalChat_ausgabe);
        Thread chatrefresh = new Thread(refresh);
        chatrefresh.start();
        //chatserver.start();
        System.out.println("Chatrefresh started");
    }

    @FXML
    public void changeNameLabel(Identity identity) {
        this.identity = identity;
        nameLabel.setText("Willkommen, " + identity.getUsername());
        chatLabel.setText("Chatten als: " + identity.getUsername());
    }

    @FXML
    void showBestenliste(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/bestenliste.fxml"));
        Parent root = loader.load();

        // Todo: bestenliste Controller

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Bestenliste");
        stage.show();

    }

    public void delAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Account_löschen.fxml"));
        Parent root1 = loader.load();

        // Todo: controller of Account-löschen

        // next scene Password löschen
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("wollen Sie den Account wirklich l\u00f6schen?");
        stage.show();
    }

    public void abmelden(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/startseite.fxml"));
        Parent root = loader.load();

        // TODO: Startseite Controller
        StartseiteController controller = loader.getController();
        controller.setParams(new DatabaseProvider(true));

        // next scene öffnen
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

    public void raumBeitreten(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/gameField.fxml"));
        Parent root = loader.load();

        // TODO: Startseite Controller

        // next scene öffnen
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Spieldfeld");

        stage.setOnCloseRequest(e -> {
            // TODO: disconnect user
        });

        stage.show();
        Stage start = (Stage) button_beitreten.getScene().getWindow();
        start.close();
    }

    public void sendeNachricht() {
        String nachricht = globalChat_eingabe.getText();
        try {
            Message latestMessage = messageService.sendeNachricht(identity, nachricht);
            globalChat_ausgabe.appendText(
                    latestMessage.sender + " | " + latestMessage.date.toString() + " | " + latestMessage.content
                            + "\n");
            globalChat_eingabe.clear();
            System.out.println("Message sent " + latestMessage.content);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}