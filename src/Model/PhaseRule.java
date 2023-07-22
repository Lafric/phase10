package Model;
import java.io.Serializable;
import java.util.Arrays;


public class PhaseRule implements Serializable{
    private final Filing[] rules;

    public PhaseRule(Filing ruleOne, Filing ruleTwo){
        this.rules = new Filing[2];
        this.rules[0] = ruleOne;
        this.rules[1] = ruleTwo;
    }
    public PhaseRule(Filing[] rules){
        this.rules = rules;
    }
    
    /** 
     * @return Filing[]
     */
    public Filing[] getPhaseRules(){
        return this.rules;
    }
    public Filing getPhaseRule(int ruleNumber){
        if(0<ruleNumber && ruleNumber<this.rules.length+1){
            return this.rules[ruleNumber-1];
        } else {
            throw new RuntimeException("Requested Rule not avaiable");
        }
    }

    /**
     * The function checks if one of the Rules is fulfilled, if yes it creates a Filing of that kind
     * @param id is the identification of the returned Filing
     * @param cards are the cards to be checked
     * @return is null if no rule is fulfilled or a filing which is fulfilled
     */
    public Filing createMatchingFiling(int id, Card[] cards, int playerId){
        for(int j = 0; j < cards.length;j++){
            if(cards[j].getType()==CardType.JOKER){
                for(int k = 0; k< 12;k++){
                    Card tmp = cards[j];
                    cards[j] = new Card(cards[j].getId(),CardColor.BLUE,CardType.getForNumber(k+1));
                    Filing fill = createMatchingFiling(id,cards,playerId);
                    cards[j] = tmp;
                    if(fill != null){
                        return fill;
                    }
                }
            }
        }

        for(int i = 0; i< this.rules.length; i++){
            if(this.rules[i] instanceof Tuplet){
                Tuplet rule = (Tuplet) rules[i];
                boolean fitting = true;
                for(int j = 0; j < cards.length-1; j++){
                    if(cards[j].getType().getNumber() != cards[j+1].getType().getNumber()){
                        fitting = false;
                    }
                }
                if(fitting && cards.length>=rule.getAmount()){
                    System.err.println("Found Tuple");
                    return new Tuplet(id, cards[0].getType(),cards.length,playerId);
                }
            } else if(this.rules[i] instanceof Street){
                Street street = (Street) rules[i];
                // Check if minimum length is fulfilled
                if(street.getEnd().getNumber()-street.getStart().getNumber()+1 <= cards.length) {
                    // Order values
                    int[] values = new int[cards.length];
                    for (int j = 0; j < values.length; j++) {
                        values[j] = cards[j].getType().getNumber();
                    }
                    // Sort values and check for order
                    boolean sorted = true;
                    Arrays.sort(values);
                    for (int j = 0; j < values.length - 1; j++) {
                        if (values[j]+1 != values[j+1]) {
                            sorted = false;
                        }
                    }
                    if (sorted) {
                        System.out.println("Found Street");
                        return new Street(id, CardType.getForNumber(values[0]), CardType.getForNumber(values[values.length - 1]),playerId);
                    }
                }
            }
        }
        return null;
    }
}

