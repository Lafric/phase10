package Communication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;

import Model.Identity;

public class MessageService {

    private Registry registry;

    //public MessageService(Registry registry) {
    //    this.registry = registry;
    //}

    public MessageService() {
        try {
            this.registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message sendeNachricht(Identity identity, String nachricht,String registryName) throws Exception {
        // create new timestamp with current time
        Timestamp timestamp = Timestamp.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTime = formatter.format(timestamp);
        ServerFuncs server = (ServerFuncs) registry.lookup(registryName);
        server.sendMessageToChat(new Message(identity.getUsername(), formattedTime, nachricht));

        ArrayList<Message> messages = server.fetchMessages();
        return messages.get(messages.size() - 1);
    }


}
