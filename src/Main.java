import javax.xml.crypto.Data;

import Control.LoginManager;
import Control.MenuManager;
import Model.DatabaseProvider;
import Model.LoginResult;
// MainGui ist die neue Main... sollte gelöscht werden.
/**
 * The Main class is the initial starting point.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class Main {
    /**
     * Main function to start the program
     * 
     * @param args are non-used input parameters
     */
    public static void main(String[] args) {
       DatabaseProvider databaseProvider = new DatabaseProvider(false);
        // Let user do registration, necessary for continuation of the game
        LoginManager logger = new LoginManager(databaseProvider);
        logger.initFrame();
        LoginResult loginRes = logger.performBlockingLogin();// Should be blocking call

        // If Login successfully, open Menu for selection
        if (loginRes.success) {
            MenuManager menuManager = new MenuManager(loginRes.identity);
            menuManager.start();
        } else {
            System.err.println("Error at Login Occurred");
        }
    }
}
