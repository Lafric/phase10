package Model;

/**
 * This class represent a Tuple of Cards. Either open stacks in Game or as rules for each Phase.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Tuplet extends Filing{
    private int amount;
    private CardType type;

    public Tuplet(int id, CardType type, int amount) {
        super(id);
        this.type = type;
        this.amount = amount;
    }

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

