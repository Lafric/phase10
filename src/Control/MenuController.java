package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import Communication.Lobby;

import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import Communication.ChatRefresh;
import Communication.Message;
import Communication.MessageService;
import Communication.RMIServer;
import Communication.ServerFuncs;
import Communication.ServerFuncsImpl;
import Model.DatabaseProvider;
import Model.Game;
import Model.Identity;
import Model.UserData;

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
    public Button LobbyRefresh;
    public ListView<String> lobbylist = new ListView<String>();
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
        // chatserver.start();
        System.out.println("Chatrefresh started");
    }

    
    /** 
     * @param identity
     */
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

    public void refreshLobbyList(ActionEvent event) throws IOException {

        try {
            Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);

            ServerFuncs serverFuncs = (ServerFuncs) registry.lookup("serverfunc");
            System.out.println("Got Serverfunc for LobbyRefresh");

            ArrayList<Lobby> serverLobbys = (ArrayList<Lobby>) serverFuncs.getLobbyList();
            System.out.println("Got LobbyList from Server");

            ArrayList<String> lobbys = new ArrayList<String>();

            for (Lobby lobby : serverLobbys) {
                String lobbyName = lobby.getLobbyName() + "\t\t" + "(" + lobby.getCurrentPlayerCount() + "/6)";
                lobbys.add(lobbyName);
            }

            lobbylist.getItems().clear();

            lobbylist.getItems().addAll(lobbys);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_lobby_button(ActionEvent event) throws IOException {
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("/Graphics/gameField.fxml"));
        // Parent root = loader.load();
        Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
        System.out.println("Found registry");
        try {

            ServerFuncs serverFuncs = (ServerFuncs) registry.lookup("serverfunc");
            System.out.println("Found serverfunc");
            serverFuncs.createLobby();
            this.refreshLobbyList(event);
            // LobbyList.getChildren().add(new Label("Lobby erstellt"));

        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        controller.setParams(new DatabaseProvider(false));

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
        // get lobbyname from GUI
        // Lobbys are bound to those names to registry
        String lobbyString = lobbylist.getSelectionModel().getSelectedItem();

        String[] lobbyNameList = lobbyString.split("\t\t");
        String lobbyName = lobbyNameList[0];


        try {
            Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);

            Lobby lobby = (Lobby) registry.lookup(lobbyName);
            System.out.println("Found Lobby");

            lobby.joinLobby(identity);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        // UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/gameField.fxml"));
        Parent root = loader.load();
        GameFieldController controller = loader.getController();
        controller.give_lobby(lobbyName);
        controller.get_identity(identity);
        controller.setParams(new DatabaseProvider(false));
        System.out.println("Joined: " + lobbyName);


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Spieldfeld");

        stage.setOnCloseRequest(e -> {
            // TODO: disconnect user
            try {
                Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
                Lobby lobby = (Lobby) registry.lookup(lobbyName);
                System.out.println("Lobby found");
                lobby.leaveLobby(identity);
            } catch (RemoteException | NotBoundException remoteException) {
                remoteException.printStackTrace();
            }
            System.out.println("Connected to Server");
        });

        stage.show();
        Stage start = (Stage) button_beitreten.getScene().getWindow();
        start.close();
    }

    public void sendeNachricht() {
        String nachricht = globalChat_eingabe.getText();
        try {
            Message latestMessage = messageService.sendeNachricht(identity, nachricht, "serverfunc");
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

    public void start_Game(ActionEvent event) {
    }

    public void raumVerlassen(ActionEvent event) {
    }

}