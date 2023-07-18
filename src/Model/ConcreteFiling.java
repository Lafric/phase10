package Model;

import java.io.Serializable;

/**
 * One implementation of a Filing
 */
public class ConcreteFiling extends Filing implements Serializable{
    private String name;

    /**
     * Constructor
     * @param id filing if
     * @param unreal boolean to indicate only usage in rule
     * @param s name string
     */
    public ConcreteFiling(int id, boolean unreal, String s) {
        super(id, unreal);
        this.name = s;
    }

    /**
     * Simple constructor
     * @param id the filing id
     */
    public ConcreteFiling(int id) {
        super(id);
        this.name = null;
    }
}