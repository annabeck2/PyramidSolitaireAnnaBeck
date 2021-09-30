import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import org.junit.Test;

/**
 * Test Class for PyramidSolitaireTextualView.java. This class tests the formatting of the
 * pyramid in various states of the game.
 */
public class PyramidSolitaireTextualViewTest {

  PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();


  @Test
  public void testToString() {
    model.startGame(model.getDeck(), false, 5, 5);
    System.out.println(new PyramidSolitaireTextualView(model).toString());
    model.remove(4,2);
    assertEquals(false, model.isGameOver());

  }

  @Test (expected = IllegalStateException.class)
  public void testToStringNoGame() {
    System.out.println(new PyramidSolitaireTextualView(model).toString());
    assertEquals(false, model.isGameOver());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGameOver() {
    model.startGame(model.getDeck(), false, -1, 7);
  }
}
