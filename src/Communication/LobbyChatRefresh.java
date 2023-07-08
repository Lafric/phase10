package Communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import Communication.Message;
import Communication.RMIServer;
import Communication.ServerFuncs;
import Communication.ServerFuncsImpl;
import Model.DatabaseProvider;
import Model.Identity;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LobbyChatRefresh implements Runnable {
    public TextArea chat_ausgabe;
    public String lobbyName;


    public LobbyChatRefresh(TextArea chatbox,String lobbyName) {
        this.chat_ausgabe = chatbox;
        
        this.lobbyName = lobbyName;
        
    }


    @Override
    public void run() {
        
        try {
            Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
            System.out.println("Found registry");
            System.out.println("LobbyName: " + lobbyName);
            Lobby lobby = (Lobby) registry.lookup(lobbyName);            
            
            System.out.println("Found registry entry");
            TextArea chat_ausgabe = this.chat_ausgabe;
            
            while(true){
                
                Thread.sleep(10000);
                System.out.println("Lobby Chat Refresh");
                //chat_ausgabe.clear();

                ArrayList<Message> messages = lobby.fetchMessages();
                if(!messages.isEmpty()){
                    chat_ausgabe.clear();
                    String currentHisotry = chat_ausgabe.getText();
                    String latest_serv_message = currentHisotry.substring(currentHisotry.lastIndexOf("|") + 1).trim();
                    Message latestMessage = messages.get(messages.size() - 1);
                    if(!latestMessage.content.equals(latest_serv_message)){
                        for(Message message: messages){
                            chat_ausgabe.appendText(
                            message.sender + " | " + message.date.toString() + " | " + message.content
                                        + "\n");
                        }
                    }

                    
                }
                
                
            }
            
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}
