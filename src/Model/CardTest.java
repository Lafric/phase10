package Model;

import static org.junit.Assert.assertNotSame;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {

    Card c = new Card(1, CardColor.BLUE, CardType.JOKER);

    @Test
    void testGetColor() {
        assertEquals(CardColor.BLUE, c.getColor());
        assertNotSame(CardColor.RED, c.getColor());
    }

    @Test
    void testGetId() {
        assertEquals(1, c.getId());
    }

    @Test
    void testGetType() {
        assertEquals(CardType.JOKER, c.getType());
        assertNotSame(CardType.TWO, c.getType());
    }
}