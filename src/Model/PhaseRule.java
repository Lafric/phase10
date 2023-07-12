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
    public Filing createMatchingFiling(int id, Card[] cards){
        for(int i = 1; i< this.rules.length; i++){
            if(this.rules[i] instanceof Tuplet){
                Tuplet rule = (Tuplet) rules[i];
                boolean fitting = true;
                for(int j = 0; j < cards.length; j++){
                    if(rule.getType()!=cards[j].getType()){
                        fitting = false;
                    }
                }
                if(fitting){
                    return new Tuplet(id, rule.getType(),cards.length);
                }
            } else if(this.rules[i] instanceof Street){
                Street street = (Street) rules[i];
                // Check if minimum length is fulfilled
                if(street.getEnd().getNumber()-street.getStart().getNumber()+1> cards.length){
                    return null;
                }
                // Order values
                int[] values = new int[cards.length];
                for(int j = 0; j < values.length; j++){
                    values[j] = cards[j].getType().getNumber();
                }
                // Sort values and check for order
                Arrays.sort(values);
                for(int j = 0; j < values.length-1; j++){
                    if(values[j]+1 != values[j]){
                        return null;
                    }
                }
                return new Street(id,CardType.getForNumber(values[0]),CardType.getForNumber(values[values.length-1]));
            }
        }
        return null;
    }
}

