package Model;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;

public class IdentityTest {
    @Test
    void testGetUsername() {
        Identity i = new Identity("playerIdentity");
        assertSame("playerIdentity", i.getUsername());
    }
}
