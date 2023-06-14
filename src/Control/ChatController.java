package Control;

import Graphics.DummyChatFrame;
import Model.Identity;
import javax.swing.*;

/**
 * This class handles the Chat functions and should be seperated from Game
 * logic.
 */
public class ChatController extends Thread {
    private DummyChatFrame chatGUI;
    private Identity identity;

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
        this.chatGUI = new DummyChatFrame(this, identity);
        this.chatGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the application on close of this window
        this.chatGUI.setVisible(true);
        System.err.println("CHAT SETUP NOT IMPLEMENTED");
    }

    /**
     * This function is called for incoming Chat Messages to display in the GUI.
     *
     * @param message is the message to be displayed.
     */
    public synchronized void displayIncomingMessage(String message) {
        // Display in GUI
        System.err.println("CHAT CONTROLLER NOT IMPLEMENTED");
        chatGUI.displayIncomingMessage(message);
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

