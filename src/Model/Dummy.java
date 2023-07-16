package Model;

import java.io.Serializable;

public class Dummy implements Serializable{
    public PhaseRule[] createRules(){
        PhaseRule[] rules = new PhaseRule[10];
        rules[0] = new PhaseRule(new Street(2000,CardType.ONE, CardType.THREE),new Tuplet(2001,CardType.ONE,2));
        rules[1] = new PhaseRule(new Street(2002,CardType.ONE, CardType.FIVE),new Tuplet(2003,CardType.ONE,2));
        rules[2] = new PhaseRule(new Street(2004,CardType.ONE, CardType.FIVE),new Tuplet(2005,CardType.ONE,3));
        rules[3] = new PhaseRule(new Street(2006,CardType.ONE, CardType.FIVE),new Tuplet(2007,CardType.ONE,3));
        rules[4] = new PhaseRule(new Street(2008,CardType.ONE, CardType.FIVE),new Tuplet(2009,CardType.ONE,3));
        rules[5] = new PhaseRule(new Street(2010,CardType.ONE, CardType.FIVE),new Tuplet(2011,CardType.ONE,3));
        rules[6] = new PhaseRule(new Street(2012,CardType.ONE, CardType.FIVE),new Tuplet(2013,CardType.ONE,3));
        rules[7] = new PhaseRule(new Street(2014,CardType.ONE, CardType.FIVE),new Tuplet(2015,CardType.ONE,3));
        rules[8] = new PhaseRule(new Street(2016,CardType.ONE, CardType.FIVE),new Tuplet(2017,CardType.ONE,3));
        rules[9] = new PhaseRule(new Street(2018,CardType.ONE, CardType.FIVE),new Tuplet(2019,CardType.ONE,3));
        return rules;
    }
}
