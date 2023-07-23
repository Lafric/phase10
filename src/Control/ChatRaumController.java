package Control;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import Communication.ChatRefresh;
import Communication.Lobby;
import Communication.LobbyChatRefresh;
import Communication.MessageService;
import Model.Identity;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChatRaumController {
    public TextArea raumChat_ausgabe;
    public TextField raumChat_eingabe;
    public String lobby;
    public Identity identity;

    
    /** 
     * @param keyEvent
     */
    public void sendeNachricht_raumChat(KeyEvent keyEvent) {
    }


    @FXML
    public void initialize() {
        MessageService messageService = new MessageService();

        raumChat_eingabe.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER") && !raumChat_eingabe.getText().equals("")){
                try {
                    messageService.sendLobbyMessage(identity,raumChat_eingabe.getText(), lobby);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Timestamp timestamp = Timestamp.from(Instant.now());
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String formattedTime = formatter.format(timestamp);
                raumChat_ausgabe.appendText(identity.getUsername() + " | " + formattedTime + " | " + raumChat_eingabe.getText() + "\n");
                raumChat_eingabe.clear();
            }
        });

        //chatserver.start();
        System.out.println("Lobby refresh started");
    }

    public void get_lobby(String lobby){
        System.out.println("Chat got : " + lobby);
        this.lobby = lobby;
        System.out.println("Chat set : " + this.lobby);
    }

    public TextArea getChat_ausgabe(){
        return raumChat_ausgabe;
    }
    public void get_identity(Identity identity){
        this.identity = identity;
    }



}
