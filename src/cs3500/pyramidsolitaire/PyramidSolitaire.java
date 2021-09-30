package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;

import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import java.io.InputStreamReader;

/**
 * Factory class that allows the user to run a game.
 * Can enter the following arguments:
 * basic    -> will create a basic pyramid solitaire game with 7 rows and 3 draw cards
 * relaxed  -> will create a relaxed pyramid solitaire game with 7 rows and 3 draw cards
 * multi    -> will create a multi pyramid solitaire game with 7 rows and 3 draw cards
 * basic rowNum drawNum -> will create a basic pyramid solitaire game with numRows and drawNum
 *    basic 5 6 -> basic pyramid solitaire game with 5 rows and 6 draw cards
 *    basic 2 5 -> basic pyramid solitaire game with 2 rows and 5 draw cards
 * relaxed rowNum drawNum -> will create a relaxed pyramid solitaire game with numRows and drawNum
 *    relaxed 5 6 -> relaxed pyramid solitaire game with 5 rows and 6 draw cards
 *    relaxed 2 5 -> relaxed pyramid solitaire game with 2 rows and 5 draw cards
 * multi rowNum drawNum -> will create a multi pyramid solitaire game with numRows and drawNum
 *   multi 5 6 -> multi pyramid solitaire game with 5 rows and 6 draw cards
 *   multi 2 5 -> multi pyramid solitaire game with 2 rows and 5 draw cards
 */
public final class PyramidSolitaire {

  /**
   * Handles the three type of pyramid solitaire games. There is a model for each game and the
   * args are compared to check which type of game should be run.
   * @param args User inputted arguments for the gameplay
   */
  public static void main(String[] args) {

    StringBuffer out = new StringBuffer();
    Readable in = new InputStreamReader(System.in);
    PyramidSolitaireTextualController modelTextCont =
        new PyramidSolitaireTextualController(in, out);


    // parsing the first arg for a default game
    if (args.length == 1) {
      if (args[0].equals("basic")) {
        PyramidSolitaireCreator.create(GameType.BASIC);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.BASIC),
            PyramidSolitaireCreator.create(GameType.BASIC).getDeck(),
            true, 7, 3);
      }
      if (args[0].equals("relaxed")) {
        PyramidSolitaireCreator.create(GameType.RELAXED);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.RELAXED),
            PyramidSolitaireCreator.create(GameType.RELAXED).getDeck(),
            true, 7, 3);
      }
      if (args[0].equals("multipyramid")) {
        PyramidSolitaireCreator.create(GameType.MULTIPYRAMID);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.MULTIPYRAMID),
            PyramidSolitaireCreator.create(GameType.MULTIPYRAMID).getDeck(),
            true, 7, 3);
      }
    }

    // parsing the row and draw card numbers for non-default games
    int rowArg = 0;
    int drawArg = 0;
    if (args.length > 1) {
      if (args[0].equals("basic")) {
        rowArg = Integer.parseInt(args[1]);
        drawArg = Integer.parseInt(args[2]);
        PyramidSolitaireCreator.create(GameType.BASIC);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.BASIC),
            PyramidSolitaireCreator.create(GameType.BASIC).getDeck(),
            true, rowArg, drawArg);
      }
      if (args[0].equals("relaxed")) {
        rowArg = Integer.parseInt(args[1]);
        drawArg = Integer.parseInt(args[2]);
        PyramidSolitaireCreator.create(GameType.RELAXED);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.RELAXED),
            PyramidSolitaireCreator.create(GameType.RELAXED).getDeck(),
            true, rowArg, drawArg);
      }
      if (args[0].equals("multipyramid")) {
        rowArg = Integer.parseInt(args[1]);
        drawArg = Integer.parseInt(args[2]);
        PyramidSolitaireCreator.create(GameType.MULTIPYRAMID);
        modelTextCont.playGame(PyramidSolitaireCreator.create(GameType.MULTIPYRAMID),
            PyramidSolitaireCreator.create(GameType.MULTIPYRAMID).getDeck(),
            true, rowArg, drawArg);
      }
    }
  }
}