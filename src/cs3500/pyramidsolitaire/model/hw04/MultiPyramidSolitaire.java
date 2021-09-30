package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is an implementation of a multi pyramid solitaire game.
 * A multiPyramid solitaire game has three intertwining pyramids and follows
 * the same rules as the basic game with the new layout.
 */
public class MultiPyramidSolitaire extends BasicPyramidSolitaire
    implements PyramidSolitaireModel<Card> {

  int pyrWidth;

  public MultiPyramidSolitaire() {
    super();
    this.pyrWidth = 0;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {

    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null");
    }

    if (numRows <= 0 || numDraw < 0) {
      throw new IllegalArgumentException("Cannot have negative rows or draw cards");
    }
    if (numRows > 8) {
      throw new IllegalArgumentException("too many rows");
    }
    if (deck.size() != 104) {
      throw new IllegalArgumentException("Deck incorrect size");
    }
    if (!getDupes(deck)) {
      throw new IllegalArgumentException("Cannot have more than 2 of the same cards");
    }

    if (!isDeckValid(deck)) {
      throw new IllegalArgumentException("Invalid deck");
    }
    int overlap = 0;
    int numCardsInPyramid = 0;

    if (numRows % 2 == 0) {
      overlap = numRows / 2;
    }
    else {
      overlap = numRows / 2 + 1;
    }

    int rowsWithGaps = numRows - overlap - 1;

    if (numRows % 2 == 0) {
      pyrWidth = numRows + overlap + rowsWithGaps + 1;
    }
    else {
      pyrWidth = numRows + overlap + rowsWithGaps;
    }

    super.resetGame();
    //this.position = new Card[numDraw][numRows];
    this.deck = new ArrayList<Card>(deck);
    this.numRows = numRows;
    this.numDraw = numDraw;
    indexOfCard = 0;

    if (shuffle) {
      Collections.shuffle(this.deck);
    }

    position = new Card[numRows][pyrWidth];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < pyrWidth; j++) {
        Card dummyCard = new Card(Suit.Heart, Rank.Eight);
        dummyCard.setCardUsed();
        position[i][j] = dummyCard;
      }
    }

    //populating the pyramid -> goes through the cards needed to populate the pyramid
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= pyrWidth - numRows + i; j++) {
        if (rowsWithGaps - i > 0) {
          for (int p = 0; p < i + 1; p++) {
            if ((j + p) >= pyrWidth - i) {
              continue;
            }
            position[i][j + p] = this.deck.get(indexOfCard);
            numCardsInPyramid++;
            indexOfCard++;
          }
          j += i;
          for (int gap = 0; gap < rowsWithGaps - i; gap++) {
            j++; //skip
          }
        }
        else {
          position[i][j] = this.deck.get(indexOfCard);
          numCardsInPyramid++;
          indexOfCard++;
        }
      }
    }

    // cannot have a index of 0 -> means there are no cards in pyramid
    if (indexOfCard == 0) {
      throw new IllegalArgumentException("There are no cards here!!");
    }

    //populating the stock pile, starting from the card we left off at, go through the rest of the
    // deck and put them in the draw pile
    for (int i = indexOfCard; i < this.deck.size(); i++) {
      stockPile.add(this.deck.get(i));
      indexOfCard++;
    }

    // number of draw cards cannot be greater than the stock size
    if (numDraw > stockPile.size()) {
      this.numRows = -9999;
      throw new IllegalArgumentException("Cannot have more draw cards than in the pile");
    }

    // if the stock cards and number of cards in the pyramid are greater than 52,
    // the game is invalid
    if (numCardsInPyramid + stockPile.size() > 104) {
      this.numRows = -9999;
      throw new IllegalArgumentException("Too many cards");
    }

    // set the cards from 0 to numDraw to be visible
    for (int i = 0; i < numDraw; i++) {
      stockPile.get(i).setVisible();
    }

    System.out.println(deck);
    System.out.println(this.deck);
  }

  /**
   * Return a valid and complete double deck of cards for a game of Pyramid Solitaire. There is no
   * restriction imposed on the ordering of these cards in the deck. The validity of the deck is
   * determined by the rules of the specific game in the classes implementing this interface.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    // create a new double deck
    List<Card> dummyDeck = new ArrayList<>();
    int index = 0;
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        dummyDeck.add(index, new Card(s, r));
        index++;
      }
    }
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        dummyDeck.add(index, new Card(s, r));
        index++;
      }
    }
    return dummyDeck;
  }


  private void printMultiPyramid() {
    //printing out the intersecting rows
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numRows - i - 1; j++) {
        System.out.print("  ");
      }
      // 10 11 12 13
      //printing out the intersecting cards
      for (int k = 0; k <= pyrWidth - numRows + i; k++) {
        if (!(position[i][k].getCardUsed())) {
          if (position[i][k].getRank().getValue() == 10) {
            System.out.print(position[i][k] + " ");
          } else {
            System.out.print(position[i][k] + "  ");
          }
        } else if (position[i][k].getCardUsed()) {
          if (k == i) {
            System.out.println(" .");
          } else {
            System.out.print(" .  ");
          }
        }
      }
      System.out.println();
    }
    if (stockPile.isEmpty()) {
      System.out.print("Draw:");
    } else {
      System.out.print("Draw: ");
    }
    // printing DRAW pile
    for (int i = 0; i < stockPile.size(); i++) {
      if (!stockPile.get(i).getVisible()) {
        continue;
      }
      if (numDraw - 1 != i) {
        System.out.print(stockPile.get(i) + ", ");
      } else {
        System.out.print(stockPile.get(i));
      }
    }
    System.out.println();
  }

  /**
   * Return a boolean value to if the deck is valid or not. This must return whether or not the deck
   * is valid.
   *
   * @param invDeck An invalid deck that is entered into the game
   * @return false: the deck is not valid, or true: the deck is valid
   */
  @Override
  protected boolean isDeckValid(List<Card> invDeck) {
    // decks cannot be null
    if (invDeck == null) {
      return false;
    }
    //size of a valid deck must be 52
    if (invDeck.size() != 104) {
      return false;
    }
    // make sure that all values are unique in the deck
    Set<Card> unique = new HashSet<Card>(this.getDeck());
    return unique.containsAll(invDeck) && invDeck.containsAll(unique);
  }

  /**
   * Check the List of cards to make sure that there are no
   * more than two duplicate cards in the deck.
   *
   * @param deck A list of Cards
   * @return boolean value, for if there are more than 2 dupes in the list of cards
   */
  // TODO: THIS
  private boolean getDupes(List<Card> deck) {
    int [] array = new int[52];

    // populate the array of 52 cards with number of elements
    for (int i = 0; i < deck.size(); i++) {
      // hashcode must be valid: 1 to 52
      if (deck.get(i).hashCode() <= 52 && deck.get(i).hashCode() >= 0) {
        array[deck.get(i).hashCode() - 1]++;
      }
      else {
        return false;
      }
    }

    // make sure there are 2 instances of each card in the array
    for (int i = 0; i < 52; i++) {
      if (array[i] != 2) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the width of the requested row, measured from the leftmost card to the rightmost card
   * (inclusive) as the game is initially dealt.
   *
   * @param row the desired row (0-indexed)
   * @return the number of spaces needed to deal out that row
   * @throws IllegalArgumentException if the row is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public int getRowWidth(int row) {
    gameStartCheck();
    // cannot get row width of a row if it is not in the pyramid
    if (row >= numRows) {
      throw new IllegalArgumentException("Invalid Row");
    }
    else {
      return pyrWidth + row - numRows + 1;
    }
  }

  /**
   * Returns the card at the specified coordinates.
   *
   * @param row  row of the desired card (0-indexed from the top)
   * @param card column of the desired card (0-indexed from the left)
   * @return the card at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   * @throws IllegalStateException    if the game hasn't been started yet
   */
  @Override
  public Card getCardAt(int row, int card) {
    gameStartCheck();
    // Illegal Argument Exception if the card has been removed, or
    // there is no card to be selected at the given position
    if (numRows < row || pyrWidth < card) {
      throw new IllegalArgumentException("Coordinates are invalid");
    }
    // if the card was used we return null
    if (position[row][card].getCardUsed()) {
      return null;
    }
    // if the card has not been used, we return the card
    else {
      return position[row][card];
    }
  }

  /**
   * Return the current score, which is the sum of the values of the cards remaining in the
   * pyramid.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public int getScore() {
    gameStartCheck();
    if (position.length == 0) {
      return -1;
    }

    int scoreOfGame = 0;
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= pyrWidth - numRows + i; j++) {
        if (!(this.position[i][j].getCardUsed())) {
          scoreOfGame = scoreOfGame + position[i][j].getRank().getValue();
        }
      }
    }
    return scoreOfGame;
  }


}
