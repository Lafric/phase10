package Communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Model.Identity;

public interface Lobby  extends Remote{
    
    public Integer getCurrentPlayerCount() throws RemoteException; 
    public Integer getMaxPlayerCount() throws RemoteException; 
    public String getLobbyName() throws RemoteException;
    public void joinLobby(Identity identity) throws RemoteException;
    public void leaveLobby(Identity identity) throws RemoteException;
    public ArrayList<Message> fetchMessages() throws RemoteException;
    public void sendMessage(Message message) throws RemoteException;
}
