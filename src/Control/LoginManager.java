package Control;

import Graphics.DummyDialog;
import Model.DatabaseProvider;
import Model.LoginResult;

import javax.swing.*;
import Model.AuthInterface;
import Model.AuthResult;
import Model.Identity;
import Model.LoginResult;

/**
 * The LoginManager handles the login process, including account creation,
 * password checking and so on.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class LoginManager {

    private DatabaseProvider databaseProvider;
    private DummyDialog dialogFrame;

    /**
     * Constructor to set up the LoginManager
     *
     * @param databaseProvider is the database provider to use
     */
    public LoginManager(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    private AuthResult performLogin(String name, String pw) {
        System.out.println("Logging in..");
        if (this.databaseProvider.checkUser(name, pw)) {
            System.out.println("Valid name and password");
            return new AuthResult(true, "");
        } else {
            return new AuthResult(false, "Invalid name or password");
        }
    }

    public AuthResult performRegistration(String name, String pw) {
        System.out.println("Registrating..");
        if (this.databaseProvider.checkUser(name, pw)) {
            return new AuthResult(false, "User already exists");
        } else {
            this.databaseProvider.addUser(name, pw);
            return new AuthResult(true, "");
        }
    }

    public void initFrame() {
        AuthInterface loginPerformer = this::performLogin;
        AuthInterface registrationPerformer = this::performRegistration;
        this.dialogFrame = new DummyDialog(loginPerformer, registrationPerformer);
        dialogFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Closes application if window is closed
    }

    public LoginResult performBlockingLogin() {
        // Create dummy frame
        boolean success = this.dialogFrame.performBlockingLogin(); // Blocking call
        if (success) {
            Identity identity = new Identity(this.dialogFrame.getName());
            return new LoginResult(success, "", identity);
        } else {
            return new LoginResult(false, "Login failed", null);
        }

    }

}

