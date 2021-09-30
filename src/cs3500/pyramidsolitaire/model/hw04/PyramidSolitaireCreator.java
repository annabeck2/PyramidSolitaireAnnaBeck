package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Class for the Pyramid Solitaire Creator. Contains three types of games and allows
 * for them to be created.
 */
public class PyramidSolitaireCreator {

  /**
   * Enum for GameType, which can be a Basic game, a Relaxed game or a Multipyramid game.
   */
  public enum GameType {
    BASIC, RELAXED, MULTIPYRAMID;
  }

  /**
   * Allows for the GameType to be created based on the user input.
   * @param game the type of game we want to play
   * @return the new game that we will be playing.
   */
  public static PyramidSolitaireModel create(GameType game) {
    switch (game) {
      case BASIC:
        return new BasicPyramidSolitaire();
      case RELAXED:
        return new RelaxedPyramidSolitaire();
      case MULTIPYRAMID:
        return new MultiPyramidSolitaire();
      default:
        return null;
    }
  }

}
