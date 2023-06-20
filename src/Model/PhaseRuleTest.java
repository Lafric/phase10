package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.jupiter.api.Test;

public class PhaseRuleTest {

    ConcreteFiling cf1 = new ConcreteFiling(1, true, "1");
    ConcreteFiling cf2 = new ConcreteFiling(2, false, "2");
    PhaseRule pr = new PhaseRule(cf1, cf2);
    Filing[] testFiling = { cf1, cf2 };

    @Test
    void testGetPhaseRule() {
        for (int i = 0; i < 2; i++) {
            assertSame(testFiling[i], pr.getPhaseRules()[i]);
        }
    }

    @Test
    void testGetPhaseRules() {
        assertEquals(cf2.getId(), pr.getPhaseRule(2).getId());
    }
}