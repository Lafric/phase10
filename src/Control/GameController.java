package Control;

import Communication.Update;

/**
 * This class controls game logic and handles input from other devices.
 * @author Alexander Guenther
 * @version 1.0
 */
public class GameController extends Thread{

    /**
     * The logical control unit for the game.
     */
    @Override
    public void run() {
        System.err.println("GAME CONTROLLER NOT IMPLEMENTED");
    }

    /**
     * This message can be called for updates from different device.
     */
    public synchronized void inputUpdateMessage(Update update){
        System.err.println("INPUT MESSAGE NOT IMPLEMENTED.");
    }
}

