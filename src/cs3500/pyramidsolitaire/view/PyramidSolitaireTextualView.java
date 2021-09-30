package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;
import java.util.List;

/**
 * Textual View for the Basic Pyramid Solitaire Game.
 * Contains a model, that will be used to build the String
 * representation of the Pyramid Solitaire Game.
 */
public class PyramidSolitaireTextualView {

  private PyramidSolitaireModel<?> model;
  private Appendable ap;

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable appendable) {
    this.model = model;
    this.ap = appendable;
  }

  /**
   * Returns the String output as follows:
   * An invalid game will print an empty String
   * A won game will print a "You win!"
   * A lost game will print a "Game over. Score: ##"
   * A game in progress will print the Pyramid for the current state of the game.
   *
   * @return String output for the state of the Pyramid Solitaire Game
   */
  public String toString() {

    if (model.getNumRows() <= 0) {
      return "";
    }

    int lastRowWidth = model.getRowWidth(model.getNumRows() - 1);
    String pyrString = "";
    List<Card> drawPile = (List<Card>) model.getDrawCards();
    Card[][] position = new Card[model.getNumRows()][lastRowWidth];

    if (lastRowWidth != model.getNumRows()) {

      int overlap = 0;
      int numCardsInPyramid = 0;


      if (model.getNumRows() % 2 == 0) {
        overlap = model.getNumRows() / 2;
      }
      else {
        overlap = model.getNumRows() / 2 + 1;
      }

      int rowsWithGaps = model.getNumRows() - overlap - 1;

      if (model.getNumRows() % 2 == 0) {
        lastRowWidth = model.getNumRows() + overlap + rowsWithGaps + 1;
      }
      else {
        lastRowWidth = model.getNumRows() + overlap + rowsWithGaps;
      }

      //MULTIPYRSOLITAIRE
      int index = 0;
      //populating the pyramid -> goes through the cards needed to populate the pyramid
      for (int i = 0; i < model.getNumRows(); i++) {
        for (int j = 0; j <= lastRowWidth - model.getNumRows() + i; j++) {
          if (rowsWithGaps - i > 0) {
            for (int p = 0; p < i + 1; p++) {
              if ((j + p) >= lastRowWidth - i) {
                continue;
              }
              position[i][j + p] = (Card) model.getCardAt(i, j + p);
              numCardsInPyramid++;
              index++;
            }
            j += i;
            for (int gap = 0; gap < rowsWithGaps - i; gap++) {
              j++; //skip
            }
          }
          else {
            position[i][j] = (Card) model.getCardAt(i, j);
            numCardsInPyramid++;
            index++;
          }
        }
      }

    }

    else {
      // BASIC AND RELAXED PYRAMID SOLITAIRE
      int index = 0;
      //populating the pyramid -> goes through the cards needed to populate the pyramid
      for (int i = 0; i < model.getNumRows(); i++) {
        for (int j = 0; j <= i; j++) {
          position[i][j] = (Card) model.getCardAt(i, j);
          index++;
        }
      }
    }

    // game over -> WIN, score will be 0
    if (model.isGameOver() && model.getScore() == 0) {
      return "You win!";
    }

    // game over -> LOSE
    if (model.isGameOver() && model.getScore() > 0) {
      return "Game over. Score: " + model.getScore();
    }

    // Bad State -> Game not Started
    if (model.isGameOver() && model.getScore() == -1) {
      return "";
    }

    // printing pyramid
    //printing row position
    for (int i = 0; i < model.getNumRows(); i++) {
      for (int j = 0; j < model.getNumRows() - i - 1; j++) {
        //printing a space
        pyrString = pyrString + "  ";
      }
      //printing card position
      for (int k = 0; k <= lastRowWidth - model.getNumRows() + i; k++) {

        if (position[i][k] != null && !(position[i][k].getCardUsed())) {
          if (k == model.getRowWidth(i) - 1) {
            pyrString += (position[i][k].toString());
          } else {
            if (position[i][k].getRank().getValue() == 10) {
              pyrString += (position[i][k].toString() + " ");
            } else {
              pyrString += (position[i][k].toString() + "  ");
            }
          }
        } else if (position[i][k] == null || position[i][k].getCardUsed()) {
          if (k == model.getRowWidth(i) - 1) {
            pyrString += (".");
          } else {
            if (lastRowWidth != model.getNumRows()) {
              pyrString += (".   ");
            }
            else {
              pyrString += (".  ");
            }
          }
        }
      }
      pyrString += ("\n");
    }
    if (drawPile.isEmpty()) {
      pyrString += ("Draw:");
    } else {
      pyrString += ("Draw: ");
    }
    // printing DRAW pile
    for (int i = 0; i < drawPile.size(); i++) {
      if (!drawPile.get(i).getVisible()) {
        continue;
      }
      if (model.getNumDraw() - 1 != i) {
        pyrString += (drawPile.get(i).toString() + ", ");
      }
      else {
        pyrString += (drawPile.get(i).toString());
      }
    }
    return pyrString;
  }

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  public void render() throws IOException {
    ap.append(model.toString());
  }
}



