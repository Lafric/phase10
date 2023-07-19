package Model;

import java.io.Serializable;

/**
 * Dummy Class to easy in cooperate methods
 */
public class Dummy implements Serializable{
    
    /**
     * rule creating is happening inside this method
     * @return PhaseRule[]
     */
    public PhaseRule[] createRules(){
        PhaseRule[] rules = new PhaseRule[10];
        rules[0] = new PhaseRule(new Tuplet(2000,CardType.ONE, 3),new Tuplet(2001,CardType.ONE,3));
        rules[1] = new PhaseRule(new Street(2002,CardType.ONE, CardType.FOUR),new Tuplet(2003,CardType.THREE,3));
        rules[2] = new PhaseRule(new Street(2004,CardType.ONE, CardType.FOUR),new Tuplet(2005,CardType.FOUR,4));
        rules[3] = new PhaseRule(new Filing[]{new Street(2006,CardType.ONE, CardType.SEVEN)});
        rules[4] = new PhaseRule(new Filing[]{new Street(2008,CardType.ONE, CardType.EIGHT)});
        rules[5] = new PhaseRule(new Filing[]{new Street(2010,CardType.ONE, CardType.NINE)});
        rules[6] = new PhaseRule(new Tuplet(2012,CardType.ONE, 4),new Tuplet(2013,CardType.ONE,4));
        rules[7] = new PhaseRule(new Filing[]{new Street(2014,CardType.ONE, CardType.SEVEN)});
        rules[8] = new PhaseRule(new Tuplet(2016,CardType.ONE, 5),new Tuplet(2017,CardType.ONE,2));
        rules[9] = new PhaseRule(new Tuplet(2018,CardType.ONE, 5),new Tuplet(2019,CardType.ONE,3));
        return rules;
    }
}
