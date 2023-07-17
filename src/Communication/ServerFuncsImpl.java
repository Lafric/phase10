package Communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Model.GameImpl;
import Model.PhaseRule;

import java.rmi.*;
import java.rmi.server.*;


public class ServerFuncsImpl extends UnicastRemoteObject implements ServerFuncs {

    private ArrayList<Message> messages = new ArrayList<Message>();
    
    private Integer lobbycount = 0; 

    public ArrayList<Lobby> allLobbys = new ArrayList<Lobby>(); 
    
    public ServerFuncsImpl() throws RemoteException {
        super();
    }

    
    /** 
     * @return int
     */
    public int get_LobbyCount(){
        return lobbycount; 
    }

    public void inc_LobbyCount(){
        this.lobbycount += 1; 

    }
    //@Override
    public ArrayList<Lobby> getLobbyList() throws RemoteException{
        return this.allLobbys;
    }
    //@Override
    public void createLobby(){
        try {
            Integer lobbycount = this.get_LobbyCount(); 
            String lobbyCountString = lobbycount.toString(); 
            String lobbyName = "Lobby" + lobbyCountString; 
            //Create String for displaying in GUI: LobbyX 0/6
            this.inc_LobbyCount(); 

            Registry registry = LocateRegistry.getRegistry("185.162.248.237",1099);
            System.out.println("Found registry");

             
            Lobby lobby = new LobbyImpl(lobbyName);
            
            
            allLobbys.add(lobby);
            registry.bind(lobbyName,lobby);
            System.out.println("Bound " + lobbyName);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 


		


    }

    //@Override
    public void sendMessageToChat(Message msg) throws RemoteException {
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

    //@Override
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
