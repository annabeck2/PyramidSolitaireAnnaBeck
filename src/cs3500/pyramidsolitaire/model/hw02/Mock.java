package cs3500.pyramidsolitaire.model.hw02;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * MOCK CLASS for the model.
 */
public class Mock implements PyramidSolitaireModel {

  private final Appendable log;
  private BasicPyramidSolitaire model = new BasicPyramidSolitaire();

  public Mock(Appendable log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List getDeck() {
    List<Card> deck = model.getDeck();
    try {
      log.append("Starting game");
      try {
        for (int i = 0; i < deck.size(); i++) {
          log.append(deck.get(i).toString());
        }
      } catch (IOException e) {
        throw new IllegalStateException("Not able to get deck");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not start game");
    }
    return deck;
  }

  @Override
  public void startGame(List deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    try {
      log.append("Started the game");
    } catch (IOException e) {
      throw new IllegalStateException("could not start");
    }
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append("Removed cards");
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not remove");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append("Removed card");
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not remove");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append("Removed draw index and card");
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not remove");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append("Removed draw index");
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not remove");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
  }

  @Override
  public int getNumRows() {
    try {
      log.append((char) model.getNumRows());
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
    return model.getNumRows();
  }

  @Override
  public int getNumDraw() {
    try {
      log.append((char) model.getNumDraw());
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
    return model.getNumDraw();
  }

  @Override
  public int getRowWidth(int row) throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append((char) (row + 1));
      } catch (IOException e) {
        throw new IllegalArgumentException("Cannot get this row's width");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
    return row + 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    try {
      log.append("The game is over");
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
    return model.isGameOver();
  }

  @Override
  public int getScore() throws IllegalStateException {
    int score = 0;
    try {
      for (int i = 0; i < model.getNumRows(); i++) {
        for (int j = 0; i < model.getNumRows() - 1; i++) {
          score += model.getCardAt(i, j).getRank().getValue();
        }
      }
      log.append((char) score);
    } catch (IOException e) {
      throw new IllegalStateException("Game has not started");
    }
    return model.getScore();
  }

  @Override
  public Object getCardAt(int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    try {
      log.append("Game has started");
      try {
        log.append("Got card at position");
      } catch (IOException e) {
        throw new IllegalArgumentException("no card at position");
      }
    } catch (IOException e) {
      throw new IllegalStateException("game has not started");
    }
    return model.getCardAt(row, card);
  }

  @Override
  public List getDrawCards() throws IllegalStateException {
    List<Card> drawCards = model.getDrawCards();
    try {
      for (int i = 0; i < drawCards.size(); i++) {
        log.append(drawCards.get(i).toString());
      }
    } catch (IOException e) {
      throw new IllegalStateException("Not able to get deck");
    }
    return drawCards;
  }
}
