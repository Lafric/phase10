package Model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for a bot player
 */
public class Bot extends Player {
    /**
     * Constructor
     * @param id of the bot
     * @param name name of the bot
     * @throws RemoteException in case of problems
     */
    public Bot(int id, String name) throws RemoteException {
        super(id, name);
    }

    
    /** 
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isBot() throws RemoteException {
        return true;
    }
}