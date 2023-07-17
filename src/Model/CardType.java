package Model;

import java.io.Serializable;

/**
 * This enum represents the type of card. Furthermore, it is associated with a number and penalty points.
 * @author Alexander Guenther
 * @version 1.0
 */
public enum CardType implements Serializable {
    ONE (1, -5),
    TWO (2, -5),
    THREE (3, -5),
    FOUR (4, -5),
    FIVE (5, -5),
    SIX (6, -5),
    SEVEN (7, -5),
    EIGHT (8, -5),
    NINE (9, -5),
    TEN (10, -10),
    ELEVEN (11, -10),
    TWELVE (12, -10),
    SKIP (-1, -15),
    JOKER (-2, -20);

    private final int number;
    private final int penaltyPoints;

    /**
     * Constructor for type
     * @param number the card number
     * @param penaltyPoints the penalty points
     */
    CardType(int number, int penaltyPoints) {
        this.number = number;
        this.penaltyPoints = penaltyPoints;
    }

    /**
     * getter fro number
     * @return the representattive number
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * getter for penalty points
     * @return the penalty points
     */
    public int getPenaltyPoints(){
        return this.penaltyPoints;
    }

    /**
     * method to switch between enum and number
     * @param num number
     * @return corresponding card type
     */
    public static CardType getForNumber(int num){
        switch (num){
            case 1 : return CardType.ONE;
            case 2 : return CardType.TWO;
            case 3 : return CardType.THREE;
            case 4 : return CardType.FOUR;
            case 5 : return CardType.FIVE;
            case 6 : return CardType.SIX;
            case 7 : return CardType.SEVEN;
            case 8 : return CardType.EIGHT;
            case 9 : return CardType.NINE;
            case 10 : return CardType.TEN;
            case 11 : return CardType.ELEVEN;
            case 12 : return CardType.TWELVE;
            default: return CardType.JOKER;
        }
    }
}
