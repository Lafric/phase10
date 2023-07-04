package Communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface Lobby  extends Remote{
    
    public Integer getCurrentPlayer() throws RemoteException; 
    public Integer getMaxPlayer() throws RemoteException; 
    public String getLobbyName() throws RemoteException;

}
