package Communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LobbyImpl extends UnicastRemoteObject implements Lobby{
    
    protected LobbyImpl(String LobbyName) throws RemoteException {
        this.LobbyName = LobbyName; 
        System.out.println("Hallo ich lebe");
    }

    public String LobbyName;
    public static Integer maxPlayer = 6; 
    public Integer currentplayer; 
    
    
    @Override
    public String getLobbyName() throws RemoteException{
        return this.LobbyName; 
    }


    @Override
    public Integer getCurrentPlayer() throws RemoteException {
        return this.currentplayer;
    }

    @Override
    public Integer getMaxPlayer() throws RemoteException {
        return LobbyImpl.maxPlayer; 
    }


    

}
