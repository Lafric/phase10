package Communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerFuncsImpl extends UnicastRemoteObject implements ServerFuncs {

    // define messages as an arraylist of type message
    private ArrayList<Message> messages = new ArrayList<Message>();

    public ServerFuncsImpl() throws RemoteException {
        super();
    }

    @Override
    public void sendMessageToChat(Message msg) throws RemoteException {
        if (msg.content.length() == 0) {
            System.out.println("Chatnachricht darf nicht leer sein.");
            return;
        }

        if (msg.content.length() > 140) {
            System.out.println("Chatnachricht darf nicht länger als 140 Zeichen sein.");
            return;
        }

        messages.add(msg);
        return;
    }

    @Override
    public ArrayList<Message> fetchMessages() throws RemoteException {
        ArrayList<Message> result = new ArrayList<Message>();
        for (Message msg : messages) {
            result.add(msg);
        }
        return result;
    }

    // @Override
    // public void addUser(String name, String pw) throws RemoteException {
    // // TODO Auto-generated method stub

    // }

    // @Override
    // public boolean checkUser(String name, String pw) throws RemoteException {
    // // TODO Auto-generated method stub
    // return false;
    // }

    // @Override
    // public void changeUsername(String currentUsername, String newUsername) throws
    // RemoteException {
    // // TODO Auto-generated method stub

    // }

    // @Override
    // public void changePassword(String name, String currentPassword, String
    // newPassword) throws RemoteException {
    // // TODO Auto-generated method stub

    // }
}