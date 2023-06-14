package Model;

/**
 * This class represents a Street/ Series of cards.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Street extends Filing {
    private final CardType start;
    private final CardType end;

    public Street(int id, CardType start, CardType end) {
        super(id);
        this.start = start;
        this.end = end;
    }

    public CardType getStart() {
        return start;
    }

    public CardType getEnd() {
        return end;
    }
}
