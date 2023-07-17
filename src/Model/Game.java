
package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

/**
 * interface for a game object
 */
public interface Game extends Remote {
    /**
     * getter for open stack
     * @return the open stack
     * @throws RemoteException
     */
    public Stack<Card> getOpenStack() throws RemoteException;

    /**
     * getter for hidden stack
     * @return the hidden stack
     * @throws RemoteException
     */
    public Stack<Card> getHiddenStack() throws RemoteException;

    /**
     * This method determine the current player in action
     * @return is the currently active player.
     */
    public int getCurrentPlayer() throws RemoteException;

    /**
     * getter for the player
     * @return all players
     * @throws RemoteException
     */
    public Player[] getAllPlayers() throws RemoteException;

    /**
     * getter for filings
     * @return the filings
     * @throws RemoteException
     */
    public List<Filing> getFilings() throws RemoteException;

    /**
     * getter for phase rules
     * @return the phase rules
     * @throws RemoteException
     */
    public PhaseRule[] getPhaseRules() throws RemoteException;

    /**
     * This method picks the next active player.
     */
    public void goToNextPlayer() throws RemoteException;

    /**
     * This method simulates the process of drawing a card from the hidden stack
     *
     * @param player
     *            who is drawing a card
     * @param hiddenStackIndicator
     *            if the card is taken from the hidden or open stack
     */
    public void drawCard(Player player, boolean hiddenStackIndicator) throws RemoteException;

    /**
     * This method simulates the process of putting one card to the open stack
     *
     * @param player
     *            who is throwing a card.
     * @param cardId
     *            is the id of the card, which will be put on the stack.
     * @param playerId
     *            is the player to be skipped, in case a skip card is played
     */
    public void throwCard(Player player, int cardId, int playerId) throws RemoteException;

    /**
     * This method simulates the process of one player putting a card to a filing.
     *
     * @param player
     *            the active player
     * @param cardId
     *            the id of the card to put on the table
     * @param filingId
     *            the id number of the filing where the card should be added.
     * @param low
     *            indicates, in the case of a street, if the card should added at
     *            the start or end.
     */
    public void playCard(Player player, int cardId, int filingId, boolean low) throws RemoteException;

    /**
     * This method simulates the process of open up a new filing.
     *
     * @param player
     *            who is active at the moment
     * @param cardIds
     *            the id numbers of the cards to put down.
     */
    public void layCards(Player player, int[] cardIds) throws RemoteException;

    /**
     * This method put the game into the next round. So cards are new distributed.
     */
    public void goToNextRound() throws RemoteException;

    /**
     * This method checks if one Player has completed Phase 10
     *
     * @return true or false depending on if game is over
     */
    public boolean isGameOver() throws RemoteException;

    /**
     * method to add bot
     * @throws RemoteException
     */
    public void addBot() throws RemoteException;

    /**
     * This method performs one turn automatically for a bot
     * This method assumes the current player is a bot
     */
    public void playBotTurn() throws RemoteException;

}
