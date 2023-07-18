package Model;

import java.io.Serializable;

/**
 * This class represents a special group of cards, each needed in phases of the players
 * @author Alexander Guenther
 * @version 1.0
 */
public abstract class Filing implements Serializable{
    private final int id;

    private final int playerId;
    private final boolean unreal;// Indicates if the Card is real or only part of a rule

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * getter for unreal indicator
     * @return boolean unreal
     */
    public boolean isUnreal() {
        return unreal;
    }

    /**
     * Constructor
     * @param id filling id
     */
    public Filing(int id){
        this.id = id;
        this.unreal = false;
        this.playerId = -1;
    }

    /**
     * additional constructor
     * @param id of the filing
     * @param unreal indicator if unreal
     */
    public Filing(int id, boolean unreal) {
        this.id = id;
        this.unreal = unreal;
        this.playerId = -1;
    }

    /**
     * Another constructor
     * @param id filing id
     * @param playerId player id
     */
    public Filing(int id, int playerId) {
        this.id = id;
        this.unreal = false;
        this.playerId = playerId;
    }

    /**
     * getter for player id
     * @return player id
     */
    public int getPlayerId(){
        return this.playerId;
    }
}

