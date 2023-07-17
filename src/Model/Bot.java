package Model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {

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