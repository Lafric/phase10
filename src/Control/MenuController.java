package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import Communication.Message;
import Communication.RMIServer;
import Communication.ServerFuncs;
import Communication.ServerFuncsImpl;
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

    @FXML
    public void initialize() {
        Thread chatserver = new Thread(new RMIServer());
        chatserver.start();
        System.out.println("Chatserver gestartet");
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
            // create new timestamp with current time
            Timestamp timestamp = Timestamp.from(Instant.now());
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            String formattedTime = formatter.format(timestamp);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerFuncs server = (ServerFuncs) registry.lookup("serverfunc");
            server.sendMessageToChat(new Message(this.identity.getUsername(), formattedTime, nachricht));

            ArrayList<Message> messages = server.fetchMessages();
            Message latestMessage = messages.get(messages.size() - 1);
            globalChat_ausgabe.appendText(
                    latestMessage.sender + " | " + latestMessage.date.toString() + " | " + latestMessage.content
                            + "\n");
            globalChat_eingabe.clear();
            System.out.println("Message sent" + latestMessage.content);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
