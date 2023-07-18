package Model;

/**
 * This enum represents the color of a card.
 * @author Alexander Guenther
 * @version 1.0
 */
public enum CardColor implements java.io.Serializable{
    YELLOW,RED,GREEN,PINK,BLUE;

    /**
     * This method return a color for each integer
     * @param num given integer to convert to color
     * @return the color
     */
    public static CardColor numToCol(int num){
        switch (num){
            case 0: return YELLOW;
            case 1: return RED;
            case 2: return GREEN;
            case 3: return PINK;
            default: return BLUE;
        }
    }
}
