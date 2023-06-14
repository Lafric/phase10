package Control;

import Graphics.DummyFrame;
import Model.Identity;
import javax.swing.*;

/**
 * The MenuManager sets up the Menu and controls the selecting and starting of
 * connections.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class MenuManager extends Thread {

    private JFrame menu;
    private Identity identity;

    /**
     * Constructor to set up the MenuManager
     *
     * @param identity is the identity of the user
     */
    public MenuManager(Identity identity) {
        this.identity = identity;
    }

    /**
     * This function sets up the Main Menu GUI and handles the user input.
     */
    @Override
    public void run() {
        this.displayGUI(); // Start GUI
        ChatController chat = new ChatController(identity); // Start Chat
        chat.start();
        // Potential other stuff to start or do
    }

    /**
     * This method sets up the menu GUI.
     */
    private void displayGUI() {
        this.menu = new DummyFrame(this, identity);
        this.menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menu.setVisible(true);
        System.err.println("MENU GUI NOT IMPLEMENTED");
    }

    /**
     * This method handles start button action.
     */
    public synchronized void startGame() {
        System.err.println("GAME SHOULD BE STARTED - BUT IS NOT IMPLEMENTED");
        menu.setVisible(false);
    }

    /**
     * This function handles some action.
     */
    public synchronized void doMenuFunction1() {
        System.err.println("SOMETHING SHOULD HAPPEN - BUT IS NOT IMPLEMENTED");
    }
}
