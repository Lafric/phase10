package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Player represents one Player inside a Game.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Player {
    private final int id;
    private final String name;
    private int points;
    private int phase;
    private final List<Card> handCards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.points = 0;
        this.phase = 0;
        this.handCards = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public int getPhase() {
        return this.phase;
    }

    public List<Card> getHandCards() {
        return this.handCards;
    }

    /**
     * The method increases the penalty points of the player by the corresponding amount of his hand cards
     */
    public void increasePointsByHandCards(){
        for(Card card: this.handCards){
            this.points += card.getType().getPenaltyPoints();
        }
    }

    public void increasePhase(){
        this.phase++;
    }

    public void resetPoints(){
        this.points = 0;
    }
    public void resetPhase(){
        this.phase = 0;
    }

    public void resetHandCards(){
        this.handCards.clear();
    }
}

