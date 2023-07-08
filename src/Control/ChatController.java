package Control;
import Model.Identity;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

/**
 * This class handles the Chat functions and should be seperated from Game
 * logic.
 */
public class ChatController extends Thread {
    private Identity identity;
    public TextArea raumChat_ausgabe;
    public TextField raumChat_eingabe;
    public String lobby;

    /**
     * Constructor to set up the ChatController
     *
     * @param identity is the identity of the user
     */
    public ChatController(Identity identity) {
        this.identity = identity;
    }

    /**
     * Thread call to set up chat functionalities to run in parallel to game.
     * TO DO - Implementation - TO DO
     */
    @Override
    public void run() {
        raumChat_eingabe.setOnKeyPressed(event -> {
            if(event.getCode().toString().equals("ENTER")){
                raumChat_ausgabe.appendText(raumChat_eingabe.getText() + "\n");
                raumChat_eingabe.clear();
            }
        });
    }

    /**
     * This function is called for incoming Chat Messages to display in the GUI.
     *
     * @param message is the message to be displayed.
     */
    public synchronized void displayIncomingMessage(String message) {
        // Display in GUI
        System.err.println("CHAT CONTROLLER NOT IMPLEMENTED");
    }

    /**
     * This function is called for outgoing Chat Messages to display in the GUI.
     *
     * @param message is the message to be displayed.
     */
    public synchronized void displayOutgoingMessage(String message) {
        // Display in GUI
        System.err.println("CHAT CONTROLLER NOT IMPLEMENTED");
    }
  
}

