package Model;

import java.util.List;

/**
 * This Class represents Game that is currently being played.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Game {
    private final Player[] allPlayer;
    private Player currentPlayer;
    private PhaseRule[] phaseRules;
    private List<Card> hiddenStack;
    private List<Card> openStack;
    private List<Filing> filings;

    public Game(Player[] allPlayer, PhaseRule[] phaseRules) {
        this.allPlayer = allPlayer;
        this.phaseRules = phaseRules;
        initializeCards();
        pickCurrentPlayer();
    }

    /**
     * This method picks a starting player for the next round.
     */
    private void pickCurrentPlayer(){
        System.err.println("INITIALIZATION OF RANDOM PLAYER NOT IMPLEMENTED");
    }

    /**
     * The method initializeCards is shuffling the cards.
     */
    private void initializeCards() {
        System.err.println("RANDOM CARD HAS NOT BEEN IMPLEMENTED");
    }

    /**
     * This methods determine the current player in action
     * @return is the currently active player.
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    public Player[] getAllPlayer(){
        return this.allPlayer;
    }
    public boolean isPhaseOver(){
        System.err.println("PHASE FINISHED CHECKER NOT IMPLEMENTED");
        return true;
    }

    /**
     * This method simulates the process of drawing a card
     * @param player who is drawing a card
     * @param hiddenStack if the card is taken from the hidden or open stack
     */
    public void drawCard(Player player, boolean hiddenStack){
        System.err.println("DRAW CARD METHOD NOT IMPLEMENTED");
    }

    /**
     * This method simulates the process of putting one card to the open stack
     * @param player who is throwing a card.
     * @param cardId is the id of the card, which will be put on the stack.
     */
    public void throwCard(Player player, int cardId){
        System.err.println("THROW CARD METHOD NOT IMPLEMENTED");
    }

    /**
     * This method simulates the process of one player putting a card to a filing.
     * @param player the active player
     * @param cardId the id of the card to put on the table
     * @param filingId the id number of the filing where the card should be added.
     */
    public void playCard(Player player, int cardId, int filingId){
        System.err.println("SINGLE CARD PLAY NOT IMPLEMENTED");
    }

    /**
     * This method simulates the process of open up a new filing.
     * @param player who is active at the moment
     * @param cardIds the id numbers of the cards to put down.
     */
    public void layCards(Player player, int[] cardIds){
        System.err.println("LAY CARD METHOD NOT IMPLEMENTED");
    }
}

