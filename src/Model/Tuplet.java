package Model;

import java.io.Serializable;

/**
 * This class represent a Tuple of Cards. Either open stacks in Game or as rules for each Phase.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Tuplet extends Filing implements Serializable{
    private int amount;
    private CardType type;

    public Tuplet(int id, CardType type, int amount,int playerId) {
        super(id,playerId);
        this.type = type;
        this.amount = amount;
    }

    /**
     * another constructor for use is rule
     */
    public Tuplet(int id, CardType type, int amount) {
        super(id);
        this.type = type;
        this.amount = amount;
    }

    /** 
     * @return int
     */
    public int getAmount() {
        return amount;
    }

    public void increaseAmount(){
        this.amount++;
    }
    public CardType getType(){
        return this.type;
    }

}

