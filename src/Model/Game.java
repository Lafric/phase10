
package Model;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface Game extends Remote{
    public int getCurrentPlayer() throws RemoteException;
    public Player[] getAllPlayers() throws RemoteException;

  
    public void drawCard(Player player, boolean hiddenStack) throws RemoteException;

 
    public void throwCard(Player player, int cardId, int playerId) throws RemoteException;

    

    public void playCard(Player player, int cardId, int filingId, boolean low) throws RemoteException;


    public void layCards(Player player, int[] cardIds) throws RemoteException;
        
    
    public void goToNextRound() throws RemoteException;

    public boolean isGameOver() throws RemoteException;
        

}

