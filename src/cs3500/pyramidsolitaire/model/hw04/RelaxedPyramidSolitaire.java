package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Class for Relaxed Pyramid Solitaire. This gameplay is similiar to that of a basic game
 * but is more leniant when it comes to removing cards.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire implements
    PyramidSolitaireModel<Card> {

  /**
   * Default Constructor for Relaxed game, which calls super.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }

  /**
   * Helper Function to see if a RELAXED card is exposed, not the basic exposed way.
   *
   * @param row  the row of the card we are checking
   * @param card the card of the card we are checking
   * @return true if exposed, false if not
   */
  private boolean isRelaxedExposed(int row, int card) {
    if (row == numRows - 1) {
      return true;
    }
    return ((position[row + 1][card].getCardUsed() && !position[row + 1][card + 1].getCardUsed())
        || (!position[row + 1][card].getCardUsed() && position[row + 1][card + 1].getCardUsed())
        || (position[row + 1][card].getCardUsed() && position[row + 1][card + 1].getCardUsed()));

    //return false;
  }


  /**
   * Remove two exposed cards on the pyramid, using the two specified card positions.
   *
   * @param row1  row of first card position, numbered from 0 from the top of the pyramid
   * @param card1 card of first card position, numbered from 0 from left
   * @param row2  row of second card position
   * @param card2 card of second card position
   * @throws IllegalArgumentException if the attempted remove is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row1, int card1, int row2, int card2) {
    gameStartCheck();
    // check to make sure that the cards are in valid locations
    // needed to change to >=
    if (row1 >= numRows || row2 >= numRows
        || card1 >= getRowWidth(row1) || card2 >= getRowWidth(row2)) {
      throw new IllegalArgumentException("Index out of range");
    }

    if (row1 < 0 || row2 < 0 || card1 > getRowWidth(row1)
        || card2 > getRowWidth(row2) || card1 < 0 || card2 < 0) {
      throw new IllegalArgumentException("Index out of range");
    }

    // check to make sure the cards have not been used
    if (position[row1][card1].getCardUsed() || position[row2][card2].getCardUsed()) {
      throw new IllegalArgumentException("Cards have been used");
    }
    // do the cards add up to be 13?
    if (position[row1][card1].getRank().getValue() + position[row2][card2].getRank().getValue()
        != 13) {
      throw new IllegalArgumentException("Do not add to 13");
    }
    // if last row, we know its 13 and they can be removed
    if (row1 == numRows - 1 && row2 == numRows - 1) {
      position[row1][card1].setCardUsed();
      position[row2][card2].setCardUsed();
      deck.remove(position[row1][card1]);
      deck.remove(position[row2][card2]);
      return;
    }

    // if row 1 is in the last, but row2 is not last row
    if (row1 == numRows - 1 && row2 != numRows - 1) {
      // cover the relaxed case -> if the card we are removing
      // first check to see if the card is exposed the normal way
      if (isExposed(row2, card2)) {
        position[row1][card1].setCardUsed();
        position[row2][card2].setCardUsed();
        deck.remove(position[row1][card1]);
        deck.remove(position[row2][card2]);
        return;
      }
      // validate that the second card is available the relaxed way
      if (row2 == row1 - 1 && (card2 == card1 || card2 == card1 - 1)) {
        if (isRelaxedExposed(row2, card2)) {
          position[row1][card1].setCardUsed();
          position[row2][card2].setCardUsed();
          deck.remove(position[row1][card1]);
          deck.remove(position[row2][card2]);
          return;
        }
      } else {
        throw new IllegalArgumentException("Card is not available");
      }
    }

    // if row 2 is in the last, but row1 is not
    if (row2 == numRows - 1 && row1 != numRows - 1) {

      // normal check
      if (isExposed(row1, card1)) {
        position[row1][card1].setCardUsed();
        position[row2][card2].setCardUsed();
        deck.remove(position[row1][card1]);
        deck.remove(position[row2][card2]);
        return;
      }
      // validate that the second card is available -> RELAXED
      if (row1 == row2 - 1 && (card2 == card1 || card1 == card2 - 1)) {
        if (isRelaxedExposed(row1, card1)) {
          position[row1][card1].setCardUsed();
          position[row2][card2].setCardUsed();
          deck.remove(position[row1][card1]);
          deck.remove(position[row2][card2]);
          return;
        }
      } else {
        throw new IllegalArgumentException("Card is not available");
      }
    }

    if (row2 != numRows - 1 && row1 != numRows - 1) {
      //both cards are not in the last row
      if (isExposed(row1, card1) && (isExposed(row2, card2))) {
        position[row1][card1].setCardUsed();
        position[row2][card2].setCardUsed();
        deck.remove(position[row1][card1]);
        deck.remove(position[row2][card2]);
        return;
      }

      // relaxed way
      // row 2 on top of row 1
      // the top card is entered second
      // top one must be relaxed exposed, bottom must be normally exposed
      if (row2 == row1 - 1 && (card2 == card1 || card2 == card1 - 1)) {
        if (isRelaxedExposed(row2, card2) && isExposed(row1, card1)) {
          position[row1][card1].setCardUsed();
          position[row2][card2].setCardUsed();
          deck.remove(position[row1][card1]);
          deck.remove(position[row2][card2]);
          return;
        }
      }

      // row 1 on top of row2
      // the top card is entered first
      // top one must be relaxed exposed, bottom must be normally exposed
      if (row1 == row2 - 1 && (card2 == card1 || card1 == card2 - 1)) {
        if (isRelaxedExposed(row1, card1) && isExposed(row2, card2)) {
          position[row1][card1].setCardUsed();
          position[row2][card2].setCardUsed();
          deck.remove(position[row1][card1]);
          deck.remove(position[row2][card2]);
          return;
        }
      } else {
        throw new IllegalArgumentException("Card not usable, cannot remove");
      }
    }
  }

  @Override
  public boolean isGameOver() {
    //searching for a relaxed move
    //check for half coverage - full coverage is handled in super.isgameover
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= i; j++) {
        // dont check last row nothing under it
        if (i < (numRows - 1) && !position[i][j].getCardUsed()) {
          // normal both cards under this are avail so handled in super.isgameover
          if (position[i + 1][j].getCardUsed() && position[i + 1][j + 1].getCardUsed()) {
            continue;
          }
          // one of the two under it is available
          if (!position[i + 1][j].getCardUsed()) {
            if (position[i][j].getRank().getValue()
                + position[i + 1][j].getRank().getValue() == 13) {
              return false;
            }
          }
          else if (!position[i + 1][j + 1].getCardUsed()) {
            if (position[i][j].getRank().getValue()
                + position[i + 1][j + 1].getRank().getValue() == 13) {
              return false;
            }
          }
        }
      }
    }
    return (super.isGameOver());
  }

}