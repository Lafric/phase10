

package Model;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RemoteException;
import java.util.List;

public interface Player extends Remote{


    public int getId() throws RemoteException;

    public String getName() throws RemoteException;

    public int getPoints() throws RemoteException;

    public int getPhase() throws RemoteException;

    public List<Card> getHandCards() throws RemoteException;

    public void increasePointsByHandCards() throws RemoteException;

    public void increasePhase() throws RemoteException;

    public void resetPoints() throws RemoteException;

    public void resetPhase() throws RemoteException;

    public void resetHandCards() throws RemoteException;

}