// package Model;

// import static org.junit.Assert.assertNotSame;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;

// public class PlayerTest {

//     Card c1 = new Card(1, CardColor.RED, CardType.TWO);
//     Card c2 = new Card(2, CardColor.BLUE, CardType.EIGHT);
//     Card c3 = new Card(3, CardColor.GREEN, CardType.NINE);
//     Card c4 = new Card(4, CardColor.YELLOW, CardType.ONE);
//     Card c5 = new Card(5, CardColor.RED, CardType.FOUR);
//     Card c6 = new Card(6, CardColor.BLUE, CardType.NINE);
//     Card[] hand = { c1, c2, c3, c4, c5, c6 };

//     Player p = new Player(10, "Spieler1", 10, 1, hand);

//     @Test
//     void testGetHandCards() {
//         Card test1 = new Card(1, CardColor.RED, CardType.TWO);
//         Card test2 = new Card(2, CardColor.BLUE, CardType.EIGHT);
//         Card test3 = new Card(3, CardColor.GREEN, CardType.NINE);
//         Card test4 = new Card(4, CardColor.YELLOW, CardType.ONE);
//         Card test5 = new Card(5, CardColor.RED, CardType.FOUR);
//         Card test6 = new Card(6, CardColor.BLUE, CardType.NINE);
//         Card[] handTest = { test1, test2, test3, test4, test5, test6 };
//         for (int i = 0; i < 6; i++) {
//             assertEquals(handTest[i].getColor(), p.getHandCards()[i].getColor());
//             assertEquals(handTest[i].getId(), p.getHandCards()[i].getId());
//             assertEquals(handTest[i].getType(), p.getHandCards()[i].getType());
//         }
//     }

//     @Test
//     void testGetId() {
//         assertEquals(10, p.getId());
//     }

//     @Test
//     void testGetName() {
//         assertEquals("Spieler1", p.getName());
//     }

//     @Test
//     void testGetPhase() {
//         assertEquals(1, p.getPhase());
//     }

//     @Test
//     void testGetPoints() {
//         assertEquals(10, p.getPoints());
//     }

//     @Test
//     void testIncreasePhase() {
//         p.increasePhase();
//         p.increasePhase();
//         assertEquals(3, p.getPhase());
//     }

//     @Test
//     void testIncreasePoints() {
//         p.increasePoints(3);
//         // after increasing points of player p by 3, player p ust have 13 points, not 10
//         assertEquals(13, p.getPoints());
//         assertNotSame(10, p.getPoints());
//     }
// }
