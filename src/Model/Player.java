package Model;

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
    private final Card[] handCards;

    public Player(int id, String name, int points, int phase, Card[] handCards) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.phase = phase;
        this.handCards = handCards;
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

    public Card[] getHandCards() {
        return this.handCards;
    }

    public void increasePoints(int points){
        this.points += points;
    }

    public void increasePhase(){
        this.phase++;
    }
}

