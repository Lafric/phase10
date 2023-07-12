package Model;

import java.io.Serializable;

/**
 * This class represents a Street/ Series of cards.
 * @author Alexander Guenther
 * @version 1.0
 */
public class Street extends Filing implements Serializable{
    private CardType start;
    private CardType end;

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

    /**
     * This method decreases the start point of the street, if possible
     */
    public void lowerStart(){
        int start = this.start.getNumber();
        if(start>0){
            start--;
        }
        this.start=CardType.getForNumber(start);
    }

    /**
     * This method increases the end point of the street, if possible
     */
    public void increaseEnd(){
        int end = this.end.getNumber();
        if(end<12){
            end++;
        }
        this.end = CardType.getForNumber(end);
    }
}
