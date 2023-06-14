package Graphics;

import Control.ChatController;
import Control.MenuManager;
import Model.Identity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * This is a DUMMY JFrame in SWING to test functionalities.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class DummyChatFrame extends JFrame {
    private JTextField field;
    private final ChatController controller;
    private Identity identity;

    public DummyChatFrame(ChatController chat, Identity identity) {
        this.controller = chat;
        this.identity = identity;
        this.setSize(400, 300);
        this.getContentPane().setLayout(null);
        JButton btn1 = new JButton("Send Hello");
        JLabel nameLabel = new JLabel(identity.getUsername());
        this.field = new JTextField();
        btn1.setBounds(50, 50, 100, 50);
        field.setBounds(50, 150, 200, 50);
        nameLabel.setBounds(200, 50, 200, 50);
        btn1.setVisible(true);
        field.setVisible(true);
        nameLabel.setVisible(true);
        btn1.addActionListener(new StartListener());
        this.getContentPane().add(btn1);
        this.getContentPane().add(field);
        this.getContentPane().add(nameLabel);
        this.setVisible(false);
    }

    /**
     * Private class to listener to the Send Button
     *
     * @author Alexander Guenther
     * @version 1.0
     */
    private class StartListener implements ActionListener {
        /**
         * Action Function calls the corresponding method in te controller.
         *
         * @param e the event to be processed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.displayIncomingMessage("Hello");
        }
    }

    /**
     * Dummy implementation to display new String.
     *
     * @param txt is the text added to the current one.
     */
    public void displayIncomingMessage(String txt) {
        String str = this.field.getText();
        str += " " + txt;
        this.field.setText(str);
    }
}

