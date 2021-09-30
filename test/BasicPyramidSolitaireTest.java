import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test Class for BasicPyramidSolitaire. Tests all public methods of the class.
 */
public class BasicPyramidSolitaireTest {
  BasicPyramidSolitaire game1 = new BasicPyramidSolitaire();

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameEmptyDeck() {
    game1.startGame(null, false, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameStateAfterInvalidDeckNoShuffle() {
    game1.startGame(game1.getDeck(), false, 23, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNegRows() {
    game1.startGame(game1.getDeck(),false, -2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNegDraw() {
    game1.startGame(game1.getDeck(), false, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameRowsHigh() {
    game1.startGame(game1.getDeck(), true, 299, 9);
  }

  @Test
  public void testGetDeck() {
    List<Card> deck = game1.getDeck();
    game1.startGame(deck, false, 7, 0);
    assertEquals(new Card(Suit.Heart, Rank.Ace).hashCode(), game1.getCardAt(0,0).hashCode());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotValid2Cards() {
    game1.startGame(game1.getDeck(), false, 3, 4);
    game1.remove(0,0, 1, 2);
  }

  @Test
  public void testRemoveValid2Cards() {
    game1.startGame(game1.getDeck(), false, 5, 3);
    game1.remove(4, 0, 4, 4);
    assertEquals(null, game1.getCardAt(4,0));
  }

  @Test
  public void testRemoveJAnd2LR() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    game1.remove(6, 2, 6, 6);
    assertEquals(null, game1.getCardAt(6,2));
    assertEquals(null, game1.getCardAt(6,6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotUsable2Cards() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    game1.remove(4, 0, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotThirteen1Card() {
    game1.startGame(game1.getDeck(), false, 1, 8);
    game1.remove(0,0);
  }

  @Test
  public void testRemove1ThirteenOneCard() {
    game1.startGame(game1.getDeck(), false, 5, 7);
    game1.remove(4, 2);
    assertEquals(null, game1.getCardAt(4,2));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNotUsable1Card() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    game1.remove(4,2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveAlreadyRemoved() {
    game1.startGame(game1.getDeck(), false, 5, 0);
    game1.remove(4,2);
    game1.remove(4, 2, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKTwice() {
    game1.startGame(game1.getDeck(), false, 5, 3);
    game1.remove(4,2);
    game1.remove(4,2);
  }

  @Test(expected = IllegalStateException.class)
    public void testRemoveNoGame1Card() {
    game1.remove(2, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemoveNoGame2Cards() {
    game1.remove(2,0,3,1);
  }

  @Test
  public void testRemoveUsingDrawValid() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.removeUsingDraw(0, 6, 1);
    assertEquals(null, game1.getCardAt(6,1));
  }

  @Test
  public void testRemoveUsingDrawValid2() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.removeUsingDraw(0, 6, 1);
    assertEquals(null, game1.getCardAt(6,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemDrawWNotAvail() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    game1.removeUsingDraw(0, 3,3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveUsingDrawNot13() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.removeUsingDraw(0, 6, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveUsingDrawNot13T2() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.removeUsingDraw(1, 6, 4);
  }

  @Test
  public void testRemoveUsingDraw() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    // remove the K
    game1.remove(6, 4);
    // remove J and 2
    game1.remove(6,2,6,6);
    System.out.print(game1.getCardAt(6,6));
    // removes the 3 in draw pile and then 10
    game1.removeUsingDraw(0, 6, 1);
    // remove Q and A
    game1.remove(6,3,6,5);
    // rem 4 and 9
    game1.removeUsingDraw(0, 6, 0);
    //remove the 6 and 7
    game1.remove(5,3,5,4);
    // remove the 5 from the pile with the 8
    game1.removeUsingDraw(0,5,5);
    assertEquals(null, game1.getCardAt(5,5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUsingDrawNotVisible() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    // remove the K
    game1.remove(6, 4);
    // remove J and 2
    game1.remove(6,2,6,6);
    // removes the 3 in draw pile and then 10
    game1.removeUsingDraw(0, 6, 1);
    // remove Q and A
    game1.remove(6,3,6,5);
    // removes the 4 and the 9 from draw pile
    game1.removeUsingDraw(0, 6, 0);
    //remove the 6 and 7
    game1.remove(5,3,5,4);
    // remove the 2nd 5 from the pile with the 8
    game1.removeUsingDraw(15,5,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNotVisible() {
    game1.startGame(game1.getDeck(), false, 7, 3);
    game1.removeUsingDraw(17, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUsingDrawFail() {
    game1.startGame(game1.getDeck(), false, 7, 2);
    // remove the K
    game1.remove(6, 4);
    // remove J and 2
    game1.remove(6,2,6,6);
    // removes the 3 in draw pile and then 10
    game1.removeUsingDraw(0, 6, 1);
    // remove Q and A
    game1.remove(6,3,6,5);
    // removes the 4 and the 9 from draw pile
    game1.removeUsingDraw(0, 6, 0);
    //remove the 6 and 7
    game1.remove(5,3,5,4);
    // remove the 5 from the pile with the 8
    game1.removeUsingDraw(0,5,5);
    // try to remove 6 from draw pile with the 7 that has already been removed
    game1.removeUsingDraw(0, 5,4);
  }

  @Test
  public void testDiscardDrawNotKing() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.discardDraw(0);
    assertEquals(1, game1.getNumDraw());
  }

  @Test
  public void testDiscardDraw() {
    game1.startGame(game1.getDeck(), false, 7, 10);
    game1.discardDraw(7);
    assertEquals(10, game1.getNumDraw());
  }

  @Test
  public void testDiscardDraw2() {
    game1.startGame(game1.getDeck(), false, 7, 12);
    game1.discardDraw(10);
    assertEquals(7, game1.getNumRows());
    assertEquals(false, game1.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testDiscardDrawNoGame() {
    game1.discardDraw(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalidIndex() {
    game1.startGame(game1.getDeck(), true, 7, 0);
    game1.discardDraw(34);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtNoRows() {
    game1.getCardAt(-1,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtInvalidCoordinates() {
    game1.startGame(game1.getDeck(), true, 4, 3);
    game1.getCardAt(5,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtInvalidCoordinates2() {
    game1.startGame(game1.getDeck(), true, 7, 3);
    game1.getCardAt(4,9);
  }

  @Test
  public void testGetCardAt() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    assertEquals(new Card(Suit.Heart, Rank.Ace).hashCode(), (game1.getCardAt(0,0).hashCode()));
  }

  @Test(expected = AssertionError.class)
  public void testGetCardAt2() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    assertEquals(new Card(Suit.Heart, Rank.Ace).hashCode(), (game1.getCardAt(1,0).hashCode()));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtBadPosition() {
    game1.getCardAt(-1, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void testScoreNoGame() {
    game1.getScore();
  }

  @Test
  public void testGetScore() {
    game1.startGame(game1.getDeck(), false, 7, 1);
    game1.getScore();
    assertEquals(game1.getScore(), 185);
    // remove the K
    game1.remove(6, 4);
    // remove J and 2
    game1.remove(6,2,6,6);
    // removes the 3 in draw pile and then 10
    game1.removeUsingDraw(0, 6, 1);
    // remove Q and A
    game1.remove(6,3,6,5);
    // removes the 4 and the 9 from draw pile
    game1.removeUsingDraw(0, 6, 0);
    //remove the 6 and 7
    game1.remove(5,3,5,4);
    // remove the 5 from the pile with the 8
    game1.removeUsingDraw(0,5,5);
    assertEquals(game1.getScore(), 106);
  }

  @Test
  public void testGetNumRowsNoGame() {
    assertEquals(game1.getNumRows(), -1);
  }

  @Test
  public void testGetNumRows() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    assertEquals(game1.getNumRows(), 7);
  }

  @Test
  public void testGetNumDrawEmpty() {
    game1.startGame(game1.getDeck(), false, 7, 0);
    assertEquals(game1.getNumDraw(), 0);
  }

  @Test
  public void testGetNumDraw() {
    game1.startGame(game1.getDeck(), false, 6, 2);
    assertEquals(game1.getNumDraw(), 2);
  }

  @Test
  public void testGetRowWidth() {
    game1.startGame(game1.getDeck(), true, 7, 1);
    assertEquals(7, game1.getRowWidth(6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidthFail() {
    game1.startGame(game1.getDeck(), true, 7, 1);
    game1.getRowWidth(7);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetRowWidthNoGame() {
    game1.getRowWidth(2);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetDrawCardsNoGame() {
    game1.getDrawCards();
  }

  @Test
  public void testGetDrawCards() {
    game1.startGame(game1.getDeck(), false, 7, 2);
    game1.remove(6, 4);
    // remove J and 2
    game1.remove(6,2,6,6);
    // removes the 3 in draw pile and then 10
    game1.removeUsingDraw(0, 6, 1);
    // remove Q and A
    game1.remove(6,3,6,5);
    // removes the 4 and the 9 from draw pile
    game1.removeUsingDraw(0, 6, 0);
    assertEquals(null, game1.getCardAt(6,0));
    assertEquals(7, game1.getNumRows());
    assertEquals(2, game1.getNumDraw());
  }


  @Test
  public void testIsGameOver() {
    game1.startGame(game1.getDeck(), false, 1, 23);
    game1.removeUsingDraw(10,0,0);
    game1.discardDraw(10);
    assertEquals(true, game1.isGameOver());
  }

  @Test
  public void testGameOverFail() {
    game1.startGame(game1.getDeck(), false,  6, 4);
    assertEquals(false, game1.isGameOver());
  }

  @Test
  public void testDealCorrectlyNoShuffle() {
    game1.startGame(game1.getDeck(), false, 4, 2);
    game1.getDrawCards();
    assertEquals(4, game1.getNumRows());
    assertEquals(4, game1.getRowWidth(3));
  }

  @Test
  public void testNumDraw() {
    game1.startGame(game1.getDeck(), false, 7, 4);
    game1.getNumDraw();
    assertEquals(4, game1.getNumDraw());
  }

  @Test
  public void testDeal() {
    game1.startGame(game1.getDeck(), false, 4, 2);
    assertEquals(false, game1.isGameOver());
  }

  @Test
  public void testRemAllDraw() {
    game1.startGame(game1.getDeck(), false, 7, 4);
    for (int i = 0; i < 24; i ++) {
      game1.discardDraw(0);
    }
    assertEquals(0, game1.getNumDraw());
  }

  @Test
  public void testToString() {
    game1.startGame(game1.getDeck(), false, 7, 4);
    System.out.println(game1.toString());
    assertEquals(7, game1.getNumRows());
    assertEquals(false, game1.isGameOver());
  }

  @Test
  public void isGOver1() {
    game1.startGame(game1.getDeck(), false, 3, 4);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(0, 2,2);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(1,2,1);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(2,2,0);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(3,1,1);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(0,1,0);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(1,0,0);
    System.out.println(game1.isGameOver());
    assertEquals(true, game1.isGameOver());
  }

  @Test
  public void test0Draw() {
    game1.startGame(game1.getDeck(), false, 2, 0);
    assertEquals(true, game1.isGameOver());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGameStateAfterInvalidDeckNoShuffle1() {
    game1.startGame(game1.getDeck(), false, 1, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvRowDraw() {
    game1.startGame(game1.getDeck(), true, 3, 48);
  }

  @Test
  public void testRemKingFromDeck() {
    game1.startGame(game1.getDeck(), false, 7, 4);
    game1.remove(6,4);
    assertEquals(null, game1.getCardAt(6,4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemKingFromStockNotValid() {
    game1.startGame(game1.getDeck(), false, 7, 4);
    game1.discardDraw(10);
  }

  @Test
  public void testLosingGame() {
    game1.startGame(game1.getDeck(), false, 3, 10);
    System.out.println(game1.isGameOver());
    game1.removeUsingDraw(0, 2,2);
    game1.removeUsingDraw(0, 2,1);
    game1.removeUsingDraw(0, 2,0);
    game1.removeUsingDraw(0, 1,1);
    game1.removeUsingDraw(0, 1,0);
    game1.removeUsingDraw(0,0,0);
    System.out.println(game1.isGameOver());
    System.out.println(game1.getDeck());
    assertEquals(true, game1.isGameOver());
  }

}
