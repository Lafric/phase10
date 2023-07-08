package Control;

import java.rmi.RemoteException;

import Communication.ChatRefresh;
import Communication.Lobby;
import Communication.LobbyChatRefresh;
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

    public void sendeNachricht_raumChat(KeyEvent keyEvent) {
    }


    @FXML
    public void initialize() {
        raumChat_eingabe.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER")){
                raumChat_ausgabe.appendText(raumChat_eingabe.getText() + "\n");
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



}
