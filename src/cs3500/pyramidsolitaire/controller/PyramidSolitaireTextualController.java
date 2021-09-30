package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Class for Pyramid Solitaire Controller. Allows player to play the game.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {

  private Readable rd;
  private Appendable ap;
  private int row1;
  private int row2;
  private int card1;
  private int card2;
  private int drawCard;
  private String command;
  Scanner myScanner;

  /**
   * Accepts and stores the readable and appendable values for input and output.
   *
   * @param rd Readable input, to be stored, comes from the user
   * @param ap Appendable output, any output sent to the user
   * @throws IllegalArgumentException if either the Readable input or the Appendable input is null,
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap) {

    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Cannot have null Appendable or Readable");
    }
    this.rd = rd;
    this.ap = ap;
  }

  /**
   * The primary method for beginning and playing a game.
   *
   * @param <K>     the type of cards used by the model.
   * @param model   The game of solitaire to be played
   * @param deck    The deck of cards to be used
   * @param shuffle Whether to shuffle the deck or not
   * @param numRows How many rows should be in the pyramid
   * @param numDraw How many draw cards should be visible
   * @throws IllegalArgumentException if the model or deck is null
   * @throws IllegalStateException    if the game cannot be started, or if the controller cannot
   *                                  interact with the player.
   **/
  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {

    if (model == null || deck == null) {
      throw new IllegalArgumentException("Cannot have a null model or deck");
    }

    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Cannot start game");
    }

    myScanner = new Scanner(this.rd);// Readable
    while (myScanner.hasNext()) {
      // game over last line:
      if (model.isGameOver()) {
        break;
      }
      String state =
          new PyramidSolitaireTextualView(model).toString() + "\nScore: " + model.getScore() + "\n";



      getNextCommand();

      // quitting the game if a Q or q is found
      if (command.equals("Q") || command.equals("q")) {
        append("Game quit!\nState of game when quit:\n" + state);
        break;
      }
      // nothing valid
      else if (command.equals("")) {
        append(state);
        append("Invalid move. Play again. Unknown Command: " + command + "\n");
        continue;
      }

      try {
        switch (command) {
          case "rm1":
            model.remove(row1 - 1, card1 - 1);
            break;
          case "rm2":
            model.remove(row1 - 1, card1 - 1, row2 - 1, card2 - 1);
            break;
          case "rmwd":
            model.removeUsingDraw(drawCard - 1, row1 - 1, card1 - 1);
            break;
          case "dd":
            model.discardDraw(drawCard - 1);
            break;
          default:
            append(state);
            append("Invalid move. Play again. Unknown Command: " + command + "\n");
            break;
        }
      }
      catch (IllegalArgumentException e) {
        append("Invalid move. Play again. " + e + "\n");
        continue;
      }
      if (!model.isGameOver()) {

        state = new PyramidSolitaireTextualView(model).toString() + "\nScore: " + model.getScore()
            + "\n";
        System.out.println(state);
        append(state);
      }
      else {

        append(new PyramidSolitaireTextualView(model).toString());
      }
    }
  }

  /**
   * Appends a String to the Appendable (The output).
   *
   * @param value The String to be appended to ap.
   * @throws IllegalStateException if cannot write to appendable.
   */
  private void append(String value) throws IllegalStateException {
    try {
      ap.append(value);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to appendable");
    }
  }

  /**
   * Get the command that we need and all the integers associated with that command.
   * Populate the row1 card1 row2 card2 and drawCard with the values from the int array.
   *
   */
  private void getNextCommand() {
    row1 = 0;
    row2 = 0;
    card1 = 0;
    card2 = 0;
    drawCard = 0;
    command = "";

    // Getting the command check first for available data
    if (myScanner.hasNext()) {
      String str = myScanner.next();
      // looking for the Q/q QUIT
      if (str.contains("q") || str.contains("Q")) {
        command = "Q";
        return;
      }
      // Got a command do not know yet whether it is valid
      // We will check that below
      command = str;
    }
    else {
      // Nothing to get from scanner
      return;
    }

    // our array of the integers for the corr. commands
    int [] int_array = new int [4];

    // for remove1 -> 2 integers expected
    if (command.equals("rm1")) {
      int num = getCommandPositions(int_array, 2);
      if (!command.equals("Q") && num == 2) {
        row1 = int_array[0];
        card1 = int_array[1];
      }
    }

    // for remove2 -> 4 integers expected
    if (command.equals("rm2")) {
      int num = getCommandPositions(int_array, 4);
      if (!command.equals("Q") && num == 4) {
        row1 = int_array[0];
        card1 = int_array[1];
        row2 = int_array[2];
        card2 = int_array[3];
      }
    }

    // for remove with draw -> 3 integers expected
    if (command.equals("rmwd")) {
      int num = getCommandPositions(int_array, 3);
      if (!command.equals("Q") && num == 3) {
        drawCard = int_array[0];
        row1 = int_array[1];
        card1 = int_array[2];
      }
    }
    // for discard draw -> 1 integer expected
    if (command.equals("dd")) {
      int num = getCommandPositions(int_array, 1);
      if (!command.equals("Q") && num == 1) {
        drawCard = int_array[0];
      }
    }
  }

  /**
   * Gets the integers for the row, card and discard.
   * @param arr the array of integers
   * @param numToGet the number of integers we are expecting
   * @Returns the number of integers we found with a maximum of numToGet
   */
  private int getCommandPositions(int [] arr, int numToGet) {
    int index = 0;

    // if we find any q in the data, we need to override the command and return
    while (myScanner.hasNext() && (index != numToGet)) {
      String str = myScanner.next();
      if (str.contains("Q") || str.contains("q")) {
        command = "Q";
        return index;
      }

      // have to find integers -> if we find a non-int, we catch it and continue,
      // looking for a valid int after that.
      try {
        // populate the array
        arr[index] = Integer.parseInt(str);
      } catch (NumberFormatException e) {
        continue;
      }
      index++;
    }
    if (numToGet != index) {
      command = "";
    }
    // this is the number of integers we have already found
    return index;
  }
}