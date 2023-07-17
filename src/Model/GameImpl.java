package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * This Class represents Game that is currently being played.
 * 
 * @author Alexander Guenther
 * @version 1.0
 */
public class GameImpl extends UnicastRemoteObject implements Game {
    private boolean isGameOver;
    private Player[] allPlayer; // all participating player
    private int currentPlayer; // index on the current player
    private final PhaseRule[] phaseRules; // the rules, aka the filings the players need to put on table in each round
    private Stack<Card> hiddenStack; // hidden stack on table
    private Stack<Card> openStack; // the open stack on table
    private List<Filing> filings; // the filings on the table

    private final boolean[] playerOverloadIndicator;// Indicates if the player has one card too much due to drawing

    private final int[] skipCounter; // counts how many skips are currently on each player

    
    /** 
     * @return List<Filing>
     * @throws RemoteException
     */
    public List<Filing> getFilings() throws RemoteException {
        return filings;
    }

    public PhaseRule[] getPhaseRules() throws RemoteException {
        return phaseRules;
    }

    public Stack<Card> getOpenStack() throws RemoteException {
        return openStack;
    }

    @Override
    public Stack<Card> getHiddenStack() throws RemoteException {
        return hiddenStack;
    }

    public void addBot() throws RemoteException {
        Player[] newPlayer = new Player[this.allPlayer.length + 1];
        for (int i = 0; i < this.allPlayer.length; i++) {
            newPlayer[i] = this.allPlayer[i];
        }
        newPlayer[this.allPlayer.length] = new Bot(this.allPlayer.length, "Bot" + this.allPlayer.length);
        this.allPlayer = newPlayer;
    }

    public GameImpl(Player[] allPlayer, PhaseRule[] phaseRules) throws RemoteException {
        this.allPlayer = allPlayer;
        // Reset Players
        for (Player player : this.allPlayer) {
            player.resetPhase();
            player.resetPoints();
        }
        this.phaseRules = phaseRules;
        // Distribute Cards
        System.err.println("Started Cards");
        initializeCards();
        System.out.println("Ended Cards");
        // Pick starting player
        currentPlayer = 0;
        // Set overload to false
        this.playerOverloadIndicator = new boolean[allPlayer.length];
        Arrays.fill(this.playerOverloadIndicator, false);
        // Set up Filling Container
        this.filings = new ArrayList<>();
        // Set up Skip Counter
        this.skipCounter = new int[allPlayer.length];
        Arrays.fill(this.skipCounter, 0);
        this.isGameOver = false;
    }

    /**
     * This method picks the next active player.
     */
    public void goToNextPlayer() throws RemoteException {
        if (this.currentPlayer == this.allPlayer.length - 1) {
            this.currentPlayer = 0;
        } else {
            this.currentPlayer++;
        }
        // Check if Player gets skipped
        if (this.skipCounter[currentPlayer] > 0) {
            this.skipCounter[currentPlayer]--;
            this.goToNextPlayer();
        }
    }

    /**
     * The method initializeCards is shuffling and redistributing of the cards.
     */
    private void initializeCards() throws RemoteException {
        // Setup array and counter
        int id = 0;
        hiddenStack = new Stack<>();
        // Create all numbers
        for (int i = 0; i < 4; i++) {
            // Do it twice
            for (int q = 0; q < 2; q++) {
                for (int j = 1; j < 13; j++) {
                    // Ensure unique id
                    boolean unique = false;
                    while (!unique) {
                        unique = true;
                        for (Player player : this.allPlayer) {
                            if (player.getId() == id) {
                                unique = false;
                                id++;
                            }
                        }
                    }
                    // Create card and add to hidden stack
                    hiddenStack.push(new Card(id, CardColor.numToCol(i), CardType.getForNumber(j)));
                    id++;
                }
            }
        }
        // Create 8 Jokers
        for (int i = 0; i < 8; i++) {
            // Ensure unique id
            boolean unique = false;
            while (!unique) {
                unique = true;
                for (Player player : this.allPlayer) {
                    if (player.getId() == id) {
                        unique = false;
                        id++;
                    }
                }
            }
            hiddenStack.push(new Card(id, CardColor.BLUE, CardType.JOKER));
            id++;
        }
        // Create 4 Skips
        for (int i = 0; i < 4; i++) {
            // Ensure unique id
            boolean unique = false;
            while (!unique) {
                unique = true;
                for (Player player : this.allPlayer) {
                    if (player.getId() == id) {
                        unique = false;
                        id++;
                    }
                }
            }
            hiddenStack.push(new Card(id, CardColor.BLUE, CardType.SKIP));
            id++;
        }

        // Shuffle Cards
        Collections.shuffle(this.hiddenStack);

        // Distribute Cards to Players
        for (Player player : this.allPlayer) {
            player.resetHandCards();
            for (int i = 0; i < 10; i++) {
                Card card = this.hiddenStack.pop();
                player.getHandCards().add(card);
            }
        }

        // Put Card on open stack
        this.openStack = new Stack<>();
        this.openStack.add(this.hiddenStack.pop());

        // Remove open filings
        this.filings = new ArrayList<>();
    }

    /**
     * This method determine the current player in action
     * 
     * @return is the currently active player.
     */
    public int getCurrentPlayer() throws RemoteException {
        return this.currentPlayer;
    }

    public Player[] getAllPlayers() throws RemoteException {
        return this.allPlayer;
    }

    /**
     * This method simulates the process of drawing a card from the hidden stack
     * 
     * @param player
     *            who is drawing a card
     * @param hiddenStackIndicator
     *            if the card is taken from the hidden or open stack
     */
    public void drawCard(Player player, boolean hiddenStackIndicator) throws RemoteException {
        System.out.println("drawCard called");
        if (allPlayer[this.currentPlayer].getId() == player.getId() && !isGameOver) {
            if (!this.playerOverloadIndicator[currentPlayer]) {
                // Draw Card
                Card card = null;
                if (hiddenStackIndicator) {
                    if (this.hiddenStack.empty()) {
                        this.hiddenStack = this.openStack;
                        Collections.shuffle(this.hiddenStack);
                        this.openStack.clear();
                    }
                    System.out.println("The hidden stack has " + this.hiddenStack.size());
                    card = this.hiddenStack.pop();
                    System.out.println("Player " + allPlayer[this.currentPlayer].getName()
                            + " draws a card from the hidden stack.");
                    System.out.println("The hidden stack has " + this.hiddenStack.size() + " cards left.");
                } else {
                    card = this.openStack.pop();
                }
                // Give Card to player
                if (card != null) {
                    this.allPlayer[currentPlayer].getHandCards().add(card);
                    // Set Overload
                    this.playerOverloadIndicator[currentPlayer] = true;
                }
            } else {
                System.out.print("Player " + allPlayer[this.currentPlayer].getName() + ", you already draw one card.");
            }
        } else {
            System.out
                    .print("Player " + allPlayer[this.currentPlayer].getName() + ", it is not your turn. Please Wait.");
        }
    }

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
    public void throwCard(Player player, int cardId, int playerId) throws RemoteException {
        if (allPlayer[this.currentPlayer].getId() == player.getId() && !this.isGameOver) {
            if (this.playerOverloadIndicator[currentPlayer]) {
                // Set Overload
                this.playerOverloadIndicator[currentPlayer] = false;
                boolean success = false;
                // Check if card is on player hands
                for (Card card : player.getHandCards()) {
                    if (card.getId() == cardId) {
                        player.getHandCards().remove(card);
                        this.openStack.push(card);
                        if (card.getType() == CardType.SKIP) {
                            this.skipCounter[getPlayerIndexById(playerId)]++;
                        }
                        checkForPhaseIncrease(player);
                        success = true;
                    }
                }
                if (!success) {
                    System.err.println("WRONG CARD IDENTIFICATION NUMBER GIVEN");
                }

            } else {
                System.out.print(
                        "Player " + allPlayer[this.currentPlayer].getName() + ", you first need to draw a card.");
            }
        } else {
            System.out
                    .print("Player " + allPlayer[this.currentPlayer].getName() + ", it is not your turn. Please Wait.");
        }
    }

    /**
     * Helper method to change from player id to player index in array
     * 
     * @param id
     *            of the player
     * @return the index in array
     */
    private int getPlayerIndexById(int id) throws RemoteException {
        for (int i = 0; i < this.allPlayer.length; i++) {
            if (this.allPlayer[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

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
    public void playCard(Player player, int cardId, int filingId, boolean low) throws RemoteException {
        Card cardInFocus = null;
        if (this.allPlayer[currentPlayer].getId() == player.getId() && !isGameOver) {
            if (this.playerOverloadIndicator[currentPlayer]) {
                // Check if card is on player hands
                boolean inHand = false;
                for (Card card : player.getHandCards()) {
                    if (card.getId() == cardId) {
                        cardInFocus = card;
                        inHand = true;
                    }
                }
                if (inHand) {
                    // Check if filing is on the table
                    boolean onTable = false;
                    for (Filing filing : this.filings) {
                        if (filing.getId() == filingId) {
                            if (filing instanceof Street) {
                                // Check if Card fits at street ends
                                Street street = (Street) filing;
                                if (street.getStart().getNumber() - 1 == cardInFocus.getType().getNumber() && low) {
                                    street.lowerStart();
                                    this.allPlayer[currentPlayer].getHandCards().remove(cardInFocus);
                                } else if (street.getEnd().getNumber() + 1 == cardInFocus.getType().getNumber()
                                        && !low) {
                                    street.increaseEnd();
                                    this.allPlayer[currentPlayer].getHandCards().remove(cardInFocus);
                                } else if (cardInFocus.getType() == CardType.JOKER) {
                                    if (low) {
                                        street.lowerStart();
                                    } else {
                                        street.increaseEnd();
                                    }
                                    this.allPlayer[currentPlayer].getHandCards().remove(cardInFocus);
                                } else {
                                    System.out.println("CARD DOES NOT MATCH THE Specified FILING (Position)");
                                }
                            } else if (filing instanceof Tuplet) {
                                Tuplet tuplet = (Tuplet) filing;
                                if (tuplet.getType() == cardInFocus.getType()
                                        || cardInFocus.getType() == CardType.JOKER) {
                                    tuplet.increaseAmount();
                                    this.allPlayer[currentPlayer].getHandCards().remove(cardInFocus);
                                } else {
                                    System.out.println("CARD DOES NOT MATCH THE Specified FILING (Position)");
                                }
                            }
                            onTable = true;
                        }
                    }
                    checkForPhaseIncrease(player);
                    if (!onTable) {
                        System.out.println("SPECIFIED FILING NOT PRESENT");
                    }
                } else {
                    System.err.println("WRONG CARD IDENTIFICATION NUMBER GIVEN");
                }

            } else {
                System.out.print(
                        "Player " + allPlayer[this.currentPlayer].getName() + ", you first need to draw a card.");
            }
        } else {
            System.out
                    .print("Player " + allPlayer[this.currentPlayer].getName() + ", it is not your turn. Please Wait.");
        }
    }

    /**
     * This method simulates the process of open up a new filing.
     * 
     * @param player
     *            who is active at the moment
     * @param cardIds
     *            the id numbers of the cards to put down.
     */
    public void layCards(Player player, int[] cardIds) throws RemoteException {
        if (player.getId() == this.allPlayer[currentPlayer].getId() && !this.isGameOver) {
            // Get Rule for Phase
            PhaseRule rule = this.phaseRules[player.getPhase()];
            // Check if player has reached maximum number of own filings
            int counter = 0;
            for (Filing fill : this.filings) {
                if (fill.getPlayerId() == player.getId()) {
                    counter++;
                }
            }
            if (counter < rule.getPhaseRules().length) {
                // Determine free id
                int id = 1000;
                boolean free = false;
                while (!free) {
                    free = true;
                    for (Filing fil : this.filings) {
                        if (id == fil.getId()) {
                            free = false;
                            id++;
                        }
                    }
                }
                // Get cards
                Card[] cards = new Card[cardIds.length];
                for (int i = 0; i < cardIds.length; i++) {
                    boolean found = false;
                    for (Card plCard : player.getHandCards()) {
                        if (cardIds[i] == plCard.getId()) {
                            cards[i] = plCard;
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("The player does not hold the cards to lay");
                    }
                }
                // Check if filing exists
                Filing filing = rule.createMatchingFiling(id, cards);
                if (filing != null) {
                    this.filings.add(filing);
                    System.out.println("filings " + this.filings.size());
                    // Remove cards from player hand
                    for (int j = 0; j < cards.length; j++) {
                        System.out.println("REMOVING CARD " + cards[j].getId());
                        System.out.println("length CARD before" + player.getHandCards().size());
                        player.removeCard(cards[j]);
                        System.out.println("length CARD after" + player.getHandCards().size());
                    }
                    checkForPhaseIncrease(player);
                } else {
                    System.out.println("PLAYER " + player.getName() + ", NO MATHING FILING FOR YOUR PHASE");
                }
            }

        } else {
            System.out
                    .print("Player " + allPlayer[this.currentPlayer].getName() + ", it is not your turn. Please Wait.");
        }
    }

    /**
     * This helper method checks for a player if he/she/it increases in pahse
     * 
     * @param player
     *            to check
     */
    private void checkForPhaseIncrease(Player player) throws RemoteException {
        if (player.getHandCards().size() <= 0) {
            player.increasePhase();
            if (player.getPhase() == 10) {
                this.isGameOver = true;
            } else {
                this.goToNextRound();
            }
        }
    }

    /**
     * This method put the game into the next round. So cards are new distributed.
     */
    public void goToNextRound() throws RemoteException {
        // Save Players Points and remove cards
        for (int i = 0; i < this.allPlayer.length; i++) {
            this.allPlayer[i].increasePointsByHandCards();
            this.allPlayer[i].resetHandCards();
        }
        // Reset Skip Counter and overload
        Arrays.fill(this.skipCounter, 0);
        Arrays.fill(this.playerOverloadIndicator, false);
        // Clear fillings and redistributed cards
        this.filings.clear();
        this.openStack.clear();
        this.hiddenStack.clear();
        this.initializeCards();
    }

    /**
     * This method checks if one Player has completed Phase 10
     * 
     * @return true or false depending on if game is over
     */
    public boolean isGameOver() throws RemoteException {
        return this.isGameOver;
    }

    /**
     * This method performs one turn automatically for a bot
     * This method assumes the current player is a bot
     * @return void
     */
    public void playBotTurn() throws RemoteException {
        // Assume current player is a bot
        Player bot = this.allPlayer[this.currentPlayer];
    
        // Bot draws a card from the hidden stack
        drawCard(bot, true);
    
        // Try to lay down phase if possible
        // Here we will just make a bot try to lay down phase with all cards in its hand
        int[] cardIds = bot.getHandCards().stream().mapToInt(Card::getId).toArray();
        layCards(bot, cardIds);
    
        // Try to play a card on existing filings
        for (Card card : bot.getHandCards()) {
            for (Filing filing : this.filings) {
                // The first playable card is played
                try {
                    playCard(bot, card.getId(), filing.getId(), true);
                    break;
                } catch (Exception e) {
                    // Ignore if the card can't be played
                }
            }
        }
    
        // Finally, discard the first card in hand
        if (bot.getHandCards().size() > 0) {
            Card discardCard = bot.getHandCards().get(0);
            throwCard(bot, discardCard.getId(), -1);
        }
    }
}