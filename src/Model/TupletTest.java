package Model;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class TupletTest {
    @Test
    void testGetAmount() {
        Tuplet t = new Tuplet(1, 50);
        assertEquals(50, t.getAmount());
    }
}