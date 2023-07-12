package Model;

import java.io.Serializable;

/**
 * This class represents one card inside the Game.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Card implements Serializable{
    private final int id;
    private final CardColor color;
    private final CardType type;

    public Card(int id, CardColor color, CardType type) {
        this.id = id;
        this.color = color;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public CardColor getColor(){
        return this.color;
    }

    public CardType getType() {
        return this.type;
    }
}
