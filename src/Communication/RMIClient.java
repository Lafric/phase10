package Communication;

import java.rmi.Naming;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerFuncs server = (ServerFuncs) registry.lookup("serverfunc");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter message to send or type 'FETCH' to get messages:");
                String input = scanner.nextLine();

                // if ("FETCH".equalsIgnoreCase(input)) {
                // ArrayList<Message> messages = server.fetchMessages();
                // for (Message message : messages) {
                // System.out.println(message.content);
                // }
                // } else

                String chatString = "";

                ArrayList<Message> messages = server.fetchMessages();

                for (Message message : messages) {
                    chatString = chatString + message.content + "\n";
                }

                if ("EXIT".equalsIgnoreCase(input)) {
                    scanner.close();
                    break;
                } else {
                    server.sendMessageToChat(new Message("admin", null, input));
                }

            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}