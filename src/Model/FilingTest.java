package Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilingTest {

    ConcreteFiling cf = new ConcreteFiling(1, true, "");

    @Test
    void testGetId() {
        assertEquals(1, cf.getId());
    }

    @Test
    void testIsUnreal() {
        assertEquals(true, cf.isUnreal());
    }
}
