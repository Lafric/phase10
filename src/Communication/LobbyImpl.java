package Communication;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Model.Dummy;
import Model.Game;
import Model.GameImpl;
import Model.Identity;
import Model.Player;


    
public class LobbyImpl extends UnicastRemoteObject implements Lobby{
    
    public String LobbyName;
    public static Integer maxPlayerCount = 6; 
    public Integer currentplayerCount; 
    public ArrayList<Identity> playerlist = new ArrayList<Identity>();

    private ArrayList<Message> messages = new ArrayList<Message>();
    
    
    public Player[] create_Playerlist() throws RemoteException{
        
        
        Player[] playerarray = new Player[playerlist.size()];
        int id = 0; 

        for (int i = 0; i < playerlist.size(); i++) {
            playerarray[i] = new Player(id,playerlist.get(i).getUsername());
            id++; 
        }
        System.out.println("Playerlist created");
        return playerarray;
    }
    
    protected LobbyImpl(String LobbyName) throws RemoteException {
        this.LobbyName = LobbyName; 
        this.currentplayerCount = 0;
        
    }

    public void startGame() throws RemoteException{
        
        Player[] playerarray = create_Playerlist();
        
        Dummy dummy = new Dummy();
        
        Game game = new GameImpl(playerarray,dummy.createRules());
        
        String gamenum = this.LobbyName.substring(5);
        Registry registry = LocateRegistry.getRegistry("185.162.248.237", 1099);
        try {
            registry.bind("Game"+gamenum, game);
            System.out.println("Game"+gamenum+" bound");
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    //@Override
    public ArrayList<Message> fetchMessages() throws RemoteException {
        return this.messages;
        
    }

    
    public void sendMessage(Message msg) throws RemoteException {
        if (msg.content.length() == 0) {
            System.out.println("Chatnachricht darf nicht leer sein.");
            return;
        }

        if (msg.content.length() > 140) {
            System.out.println("Chatnachricht darf nicht leer sein.");
            return;
        }

        messages.add(msg);
        return;
    }


    

}
