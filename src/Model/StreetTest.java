package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.jupiter.api.Test;

public class StreetTest {

    Street s = new Street(1, CardType.TWO, CardType.EIGHT);

    @Test
    void testGetEnd() {
        assertEquals(CardType.TWO, s.getStart());
    }

    @Test
    void testGetStart() {
        assertNotSame(CardType.TWO, s.getEnd());
    }
}