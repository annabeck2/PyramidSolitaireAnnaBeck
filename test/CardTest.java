import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test Class to test Card Class.
 */
public class CardTest {

  @Test
  public void testToString() {
    Card myCard = new Card(Suit.Diamond, Rank.Five);
    assertEquals("5♦", myCard.toString());
  }

  @Test
  public void testToString2() {
    Card myCard = new Card(Suit.Diamond, Rank.King);
    assertEquals("K♦", myCard.toString());
  }

  @Test
  public void testIsUsed() {
    Card myCard = new Card(Suit.Club, Rank.Three);
    myCard.setCardUsed();
    assertEquals(true, myCard.getCardUsed());
  }

  @Test
  public void testEqualCards() {
    Card myCard = new Card(Suit.Club, Rank.Three);
    Card myCard2 = new Card(Suit.Club, Rank.Three);
    assertEquals(true, myCard.equals(myCard2));
  }

  @Test
  public void testNotEqualCards() {
    Card myCard = new Card(Suit.Club, Rank.Three);
    Card myCard2 = new Card(Suit.Spade, Rank.Three);
    assertEquals(false, myCard.equals(myCard2));
  }

  @Test
  public void testHashTrue() {
    Card myCard = new Card(Suit.Club, Rank.Three);
    assertEquals(42, myCard.hashCode());
  }
}
