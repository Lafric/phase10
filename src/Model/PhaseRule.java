package Model;

/**
 * The class PhaseRule represents two rules (in the standard game) that each player has to fulfill.
 * @author Alexander Guenther
 * @version 1.0
 */
public class PhaseRule {
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
        if(0<ruleNumber && ruleNumber<3){
            return this.rules[ruleNumber-1];
        } else {
            throw new RuntimeException("Requested Rule not avaiable");
        }
    }
}

