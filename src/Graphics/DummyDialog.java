package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.AuthInterface;
import Model.AuthResult;


/**
 * This class provides a blocking DUMMY JDialog in SWING to test the
 * functionality.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class DummyDialog extends JDialog {

    private JTextField nameField;
    private JTextField pwField;
    private AuthInterface LoginPerformer;
    private AuthInterface RegistrationPerformer;


    /**
     * Constructor to set up all graphic components, which are still invisible
     */
    public DummyDialog(AuthInterface performLogin, AuthInterface performRegistration) {
        super();
        this.LoginPerformer = performLogin;
        this.RegistrationPerformer = performRegistration;
        JButton loginButton = new JButton("Log in");
        JButton registerButton = new JButton("Register");
        JTextField nameField = new JTextField("Name");
        JTextField pwField = new JTextField("Password");
        loginButton.setBounds(50, 150, 100, 50);
        registerButton.setBounds(50, 220, 100, 50);
        nameField.setBounds(50, 25, 100, 50);
        this.nameField = nameField;
        this.pwField = pwField;
        pwField.setBounds(50, 75, 100, 50);
        loginButton.addActionListener(new ButtonListener(this, LoginPerformer, "Login"));
        registerButton.addActionListener(new ButtonListener(this, RegistrationPerformer, "Register"));
        this.setSize(200, 350);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(registerButton);
        this.getContentPane().add(loginButton);
        this.getContentPane().add(nameField);
        this.getContentPane().add(pwField);
        this.setVisible(false);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
    }

    public String getName() {
        return this.nameField.getText();
    }

    public String getPW() {
        return this.pwField.getText();
    }

    /**
     * This function imitates the login questioner
     *
     * @return indicates everything went successfully
     */
    public boolean performBlockingLogin() {
        this.setVisible(true);
        return true;
    }

    /**
     * Private class which implements the Button Listener for our Button.
     */
    private static class ButtonListener implements ActionListener {

        /**
         * Reference to our JDialog, in order to close the blocking view.
         */
        private DummyDialog toClose;
        private AuthInterface callback;
        private String buttonType;

        /**
         * Constructor to the parameter.
         *
         * @param toClose reference to the corresponding JDialog.
         */
        public ButtonListener(DummyDialog toClose, AuthInterface callback, String buttonType) {
            this.toClose = toClose;
            this.callback = callback;
            this.buttonType = buttonType;
        }

        /**
         * The method called in case of Action that only closes the corresponding
         * Dialog.
         *
         * @param e the event to be processed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = this.toClose.getName();
            String pw = this.toClose.getPW();
            AuthResult authres;
            authres = this.callback.execute(name, pw);

            if (authres.success) {
                if (this.buttonType.equals("Login")) {
                    System.out.println("Login successful");
                    this.toClose.setVisible(false);
                } else {
                    System.out.println("Registration successful");
                }
            } else {
                System.out.println(authres.error);
            }
        }
    }
}

