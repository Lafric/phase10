package Communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Model.Identity;

    
public class LobbyImpl extends UnicastRemoteObject implements Lobby{
    
    public String LobbyName;
    public static Integer maxPlayerCount = 6; 
    public Integer currentplayerCount; 
    public ArrayList<Identity> playerlist = new ArrayList<Identity>();
    
    
    
    
    
    protected LobbyImpl(String LobbyName) throws RemoteException {
        this.LobbyName = LobbyName; 
        this.currentplayerCount = 0;
        
    }

    @Override
    public String getLobbyName() throws RemoteException{
        return this.LobbyName; 
    }


    @Override
    public Integer getCurrentPlayerCount() throws RemoteException {
        return this.currentplayerCount;
    }

    @Override
    public Integer getMaxPlayerCount() throws RemoteException {
        return LobbyImpl.maxPlayerCount; 
    }

    @Override
    public void joinLobby(Identity identity) throws RemoteException {
        if (currentplayerCount < maxPlayerCount){ //same player *could* still join twice, maybe fix later
            this.currentplayerCount += 1;
            this.playerlist.add(identity);

        }
        else{
            //TODO implement error message in GUI
            System.out.println("couldn't join lobby");
        }

    }

    @Override
    public void leaveLobby(Identity identity) throws RemoteException {
        if(playerlist.contains(identity)){
            this.currentplayerCount -= 1; 
            this.playerlist.remove(identity);
        }
    }


    

}
