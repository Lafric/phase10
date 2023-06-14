package Graphics;

import Control.MenuManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Identity;

/**
 * This is a DUMMY JFrame in SWING to test functionalities.
 *
 * @author Alexander Guenther
 * @version 1.0
 */
public class DummyFrame extends JFrame {

    private final MenuManager controller;
    private Identity identity;

    public DummyFrame(MenuManager menu, Identity identity) {
        this.controller = menu;
        this.setSize(400, 300);
        this.getContentPane().setLayout(null);
        JButton btn1 = new JButton("Start Game");
        JButton btn2 = new JButton("Menu function 1");
        JLabel nameLabel = new JLabel("Hello " + identity.getUsername() + ", my old friend");
        btn1.setBounds(50, 50, 100, 50);
        btn2.setBounds(50, 150, 100, 50);
        btn1.setVisible(true);
        btn2.setVisible(true);
        btn1.addActionListener(new StartListener());
        btn2.addActionListener(new Function1Listener());
        nameLabel.setBounds(200, 50, 200, 50);
        this.getContentPane().add(btn1);
        this.getContentPane().add(btn2);
        this.getContentPane().add(nameLabel);
        this.setVisible(false);
    }

    /**
     * Private class to listener to the Start Button
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
            controller.startGame();
        }
    }

    /**
     * Private class to listener to the Function 1 Button
     *
     * @author Alexander Guenther
     * @version 1.0
     */
    private class Function1Listener implements ActionListener {
        /**
         * Action Function calls the corresponding method in te controller.
         *
         * @param e the event to be processed.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.doMenuFunction1();
        }
    }
}
