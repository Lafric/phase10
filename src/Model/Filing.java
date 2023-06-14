package Model;

/**
 * This class represents a special group of cards, each needed in phases of the players
 * @author Alexander Guenther
 * @version 1.0
 */
public abstract class Filing {
    private final int id;
    private final boolean unreal;// Indicates if the Card is real or only part of a rule

    public int getId() {
        return id;
    }

    public boolean isUnreal() {
        return unreal;
    }

    public Filing(int id, boolean unreal) {
        this.id = id;
        this.unreal = unreal;
    }
    public Filing(int id) {
        this.id = id;
        this.unreal = false;
    }
}

