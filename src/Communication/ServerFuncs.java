package Communication;

import java.rmi.*;
import java.util.ArrayList;


public interface ServerFuncs extends Remote {
    // chat functions
    public void 
    sendMessageToChat(Message msg) throws RemoteException;

    public ArrayList<Message> fetchMessages() throws RemoteException;

    public ArrayList<Lobby> getLobbyList() throws RemoteException;

    public void createLobby() throws RemoteException;

    // database functions
    // public void addUser(String name, String pw) throws RemoteException;

    // public boolean checkUser(String name, String pw) throws RemoteException;

    // public void changeUsername(String currentUsername, String newUsername) throws
    // RemoteException;

    // public void changePassword(String name, String currentPassword, String
    // newPassword) throws RemoteException;

}