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

    /**
     * Constructor
     * @param id of the card
     * @param color the card color
     * @param type the card type
     */
    public Card(int id, CardColor color, CardType type) {
        this.id = id;
        this.color = color;
        this.type = type;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * getter for Color
     * @return the color
     */
    public CardColor getColor(){
        return this.color;
    }

    /**
     * getter for Card type
     * @return the card type
     */
    public CardType getType() {
        return this.type;
    }

    /**
     * textual description of one card
     * @return String of card content
     */
    public String toString(){
        return ""+this.color.toString() + "-" + this.type.getNumber()+"-id:"+this.id;
    }
}
