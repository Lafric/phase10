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

public class ChatRefresh implements Runnable {
    public TextArea chat_ausgabe;

    public ChatRefresh(TextArea chatbox) {
        this.chat_ausgabe = chatbox;
    }


    @Override
    public void run() {
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerFuncs server = (ServerFuncs) registry.lookup("serverfunc");            
            
            TextArea chat_ausgabe = this.chat_ausgabe;
            
            while(true){
                
                Thread.sleep(10000);
                System.out.println("Chatrefresh");
                //chat_ausgabe.clear();

                ArrayList<Message> messages = server.fetchMessages();
                if(!messages.isEmpty()){
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
