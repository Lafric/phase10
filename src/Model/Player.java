package Model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class Player
 */
public class Player implements Serializable {
    private final int id;
    private final String name;
    private int points;
    private int phase;
    private final List<Card> handCards;

    /**
     * Constructor
     * @param id of the player
     * @param name player name
     * @throws RemoteException in case of problems
     */
    public Player(int id, String name) throws RemoteException {
        this.id = id;
        this.name = name;
        this.points = 0;
        this.phase = 0;
        this.handCards = new ArrayList<>();
    }

    /**
     * More refined constructor
     * @param id of the player
     * @param name player name
     * @param points current number of points
     * @param phase current phase
     * @param handCards current hand cards
     * @throws RemoteException in case of server problems
     */
    public Player(int id, String name, int points, int phase,  Card[] handCards) throws RemoteException {
        this.id = id;
        this.name = name;
        this.points = points;
        this.phase = phase;
        this.handCards = Arrays.asList(handCards);
    }

    
    /** 
     * @return int
     * @throws RemoteException
     */
    public int getId() throws RemoteException {
        return this.id;
    }

    /**
     * getter for name
     * @return player name
     * @throws RemoteException for server problems
     */
    public String getName() throws RemoteException {
        return this.name;
    }

    /**
     * getter for points
     * @return the points
     * @throws RemoteException in case of server problems
     */
    public int getPoints() throws RemoteException {
        return this.points;
    }

    /**
     * getter for phase
     * @return players phase
     * @throws RemoteException in case of server problems
     */
    public int getPhase() throws RemoteException {
        return this.phase;
    }

    /**
     * getter for hand cards
     * @return the handcards
     * @throws RemoteException for server problems
     */
    public synchronized List<Card> getHandCards() throws RemoteException {
        return this.handCards;
    }

    /**
     * The method increases the penalty points of the player by the corresponding
     * amount of his hand cards
     */
    public void increasePointsByHandCards() throws RemoteException {
        for (Card card : this.handCards) {
            this.points += card.getType().getPenaltyPoints();
        }
    }

    /**
     * method to increase phase by 1
     * @throws RemoteException in case of server problems
     */
    public void increasePhase() throws RemoteException {
        this.phase++;
    }

    /**
     * method to reset points to 0
     * @throws RemoteException in case of server problems
     */
    public void resetPoints() throws RemoteException {
        this.points = 0;
    }

    /**
     * reset phase to 1
     * @throws RemoteException in case of server problems
     */
    public void resetPhase() throws RemoteException {
        this.phase = 0;
    }

    /**
     * remove hand cards
     * @throws RemoteException in case of server problems
     */
    public void resetHandCards() throws RemoteException {
        this.handCards.clear();
    }

    /**
     * remove one single card from player hand
     * @param card the card to be removed
     */
    public synchronized void removeCard(Card card) {
        this.handCards.remove(card);
    }

    /**
     * indicator if player is bot
     * @return true if player is bot
     * @throws RemoteException in case of server problems
     */
    public Boolean isBot() throws RemoteException {
        return false;
    }
}
