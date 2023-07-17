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

    public boolean isUnreal() {
        return unreal;
    }

    public Filing(int id){
        this.id = id;
        this.unreal = false;
        this.playerId = -1;
    }

    public Filing(int id, boolean unreal) {
        this.id = id;
        this.unreal = unreal;
        this.playerId = -1;
    }
    public Filing(int id, int playerId) {
        this.id = id;
        this.unreal = false;
        this.playerId = playerId;
    }
    public int getPlayerId(){
        return this.playerId;
    }
}

