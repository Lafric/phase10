package Model;

/**
 * This class represent a Tuple of Cards. Either open stacks in Game or as rules for each Phase.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Tuplet extends Filing{
    private final int amount;

    public Tuplet(int id, int amount) {
        super(id);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

