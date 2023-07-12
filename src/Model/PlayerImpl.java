package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Player represents one Player inside a Game.
 * @author Alexander Guenther
 * @version 1.0
 */
public class PlayerImpl extends UnicastRemoteObject implements Player {
    private final int id;
    private final String name;
    private int points;
    private int phase;
    private final List<Card> handCards;

    public PlayerImpl(int id, String name) throws RemoteException {
        this.id = id;
        this.name = name;
        this.points = 0;
        this.phase = 0;
        this.handCards = new ArrayList<>();
    }

    public int getId() throws RemoteException{
        return this.id;
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public int getPoints()  throws RemoteException{
        return this.points;
    }

    public int getPhase()  throws RemoteException{
        return this.phase;
    }

    public List<Card> getHandCards()  throws RemoteException{
        return this.handCards;
    }

    /**
     * The method increases the penalty points of the player by the corresponding amount of his hand cards
     */
    public void increasePointsByHandCards() throws RemoteException{
        for(Card card: this.handCards){
            this.points += card.getType().getPenaltyPoints();
        }
    }

    public void increasePhase() throws RemoteException{
        this.phase++;
    }

    public void resetPoints() throws RemoteException{
        this.points = 0;
    }
    public void resetPhase() throws RemoteException{
        this.phase = 0;
    }

    public void resetHandCards() throws RemoteException{
        this.handCards.clear();
    }
}

