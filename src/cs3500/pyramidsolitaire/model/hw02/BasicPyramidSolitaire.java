package cs3500.pyramidsolitaire.model.hw02;

import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is an implementation of a basic pyramid solitaire game.
 */

public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {


  protected Card[][] position;    // position of the card
  protected List<Card> stockPile;  // list of Card in the stock Pile
  protected int indexOfCard;      // current index of card
  protected int numRows;          // number of rows the game has
  protected int numDraw;          // number of draw cards that are visible
  protected List<Card> deck;      // list of Card in the deck
  protected int numCards;         // current value for number of cards
  protected int numCardsInPyramid; // value of number of cards in the pyramid


  /**
   * Constructor for Basic Pyramid Solitaire Game to initialize variables.
   */
  public BasicPyramidSolitaire() {
    resetGame();
  }

  /**
   * Resets the game when startGame is called again.
   */
  protected void resetGame() {
    this.position = null;
    this.stockPile = new ArrayList<Card>();
    this.indexOfCard = 0;
    this.numRows = -9999;
    this.numDraw = -9999;
    this.deck = new ArrayList<Card>();
    this.numCards = -9999;
    this.numCardsInPyramid = 0;
  }


  /**
   * <p>Deal a new game of Pyramid Solitaire.
   * The cards to be used and their order are specified by the the given deck, unless the {@code
   * shuffle} parameter indicates the order should be ignored.</p>
   *
   * <p>This method first verifies that the deck is valid. It deals cards in rows
   * (left-to-right, top-to-bottom) into the characteristic pyramid shape with the specified number
   * of rows, followed by the specified number of draw cards. When {@code shuffle} is {@code false},
   * the 0th card in {@code deck} is used as the first card dealt.</p>
   *
   * <p>This method should have no other side effects, and should work for any valid arguments.</p>
   *
   * @param deck    the deck to be dealt
   * @param shuffle if {@code false}, use the order as given by {@code deck}, otherwise use a
   *                randomly shuffled order
   * @param numRows number of rows in the pyramid
   * @param numDraw number of draw cards available at a time
   * @throws IllegalArgumentException if the deck is null or invalid, the number of pyramid rows is
   *                                  non-positive, the number of draw cards available at a time is
   *                                  negative, or a full pyramid and draw pile cannot be dealt with
   *                                  the number of given cards in deck
   */
  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw) {
    // are there more than 52 cards
    if (numCards > 52) {
      throw new IllegalArgumentException("There are too many cards for this deck");
    }

    // null decks cannot be used to play a game
    if (deck == null) {
      throw new IllegalArgumentException("There are no cards in the deck");
    }

    // cannot have more than 9 rows, or less than 1 row in a valid game
    if (numRows <= 0 || numRows > 9) {
      throw new IllegalArgumentException("incorrect number of rows");
    }

    //must have more than -1 draw cards to play a valid game
    if (numDraw < 0) {
      throw new IllegalArgumentException("There must be at least 0 draws");
    }

    // duplicate cards in a deck means the deck is invalid
    if (getDupes(deck)) {
      throw new IllegalArgumentException("Cannot have dupe cards");
    }

    // cannot shuffle an incomplete deck in a valid game
    if (deck.size() < 52 && shuffle) {
      throw new IllegalArgumentException("Cannot shuffle an incomplete deck");
    }

    // deck must be valid for a game to start
    if (!isDeckValid(deck)) {
      throw new IllegalArgumentException("INVALID DECK");
    }

    // Reset the game
    resetGame();
    //this.position = new Card[numDraw][numRows];
    this.deck = new ArrayList<Card>(deck);
    this.numRows = numRows;
    this.numDraw = numDraw;
    indexOfCard = 0;

    // the entire deck is shuffled
    if (shuffle) {
      Collections.shuffle(this.deck);
    }

    position = new Card[numRows][numRows];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numRows; j++) {
        Card dummyCard = new Card(Suit.Heart, Rank.Eight);
        // dummyCard.setCardUsed();  I think not needed ctor does this for all cards
        position[i][j] = dummyCard;
      }
    }

    //populating the pyramid -> goes through the cards needed to populate the pyramid
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= i; j++) {
        //position[i][j] = this.deck.remove(0);
        position[i][j] = this.deck.get(indexOfCard);
        numCardsInPyramid++;
        indexOfCard++;
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
    if (numCardsInPyramid + stockPile.size() > 52) {
      this.numRows = -9999;
      throw new IllegalArgumentException("Too many cards");
    }

    // set the cards from 0 to numDraw to be visible
    for (int i = 0; i < numDraw; i++) {
      stockPile.get(i).setVisible();
    }
  }

  /**
   * Function that was used to print the pyramid, used for testing purposes
   * in this class only. Essentially has same purpose as toString in view.
   */
  protected void printPyramid() {
    //printing row position
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numRows - i - 1; j++) {
        //printing a space
        System.out.print("  ");
      }
      //printing card position
      for (int k = 0; k < i + 1; k++) {
        if (!(position[i][k].getCardUsed())) {
          if (k == i) {
            System.out.print(position[i][k]);
          } else {
            if (position[i][k].getRank().getValue() == 10) {
              System.out.print(position[i][k] + " ");
            } else {
              System.out.print(position[i][k] + "  ");
            }
          }
        } else if (position[i][k].getCardUsed()) {
          if (k == i) {
            System.out.print(" .");
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
   * Return a valid and complete deck of cards for a game of Pyramid Solitaire. There is no
   * restriction imposed on the ordering of these cards in the deck. The validity of the deck is
   * determined by the rules of the specific game in the classes implementing this interface.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    // create a new deck
    List<Card> dummyDeck = new ArrayList<>();
    int index = 0;
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        dummyDeck.add(index, new Card(s, r));
        index++;
      }
    }
    return dummyDeck;
  }

  /**
   * Helper function to check to make sure game has started. Checks to make sure that
   * the number of rows, the deck and the number of draw cards are valid.
   */
  protected void gameStartCheck() {
    // make sure game has been started
    if (numRows <= 0 || deck == null || numDraw < 0) {
      throw new IllegalStateException("Game not started.");
    }
  }

  /**
   * Return a boolean value to if the deck is valid or not. This must return whether or not the deck
   * is valid.
   *
   * @param invDeck An invalid deck that is entered into the game
   * @return false: the deck is not valid, or true: the deck is valid
   */
  protected boolean isDeckValid(List<Card> invDeck) {
    // decks cannot be null
    if (invDeck == null) {
      return false;
    }
    //size of a valid deck must be 52
    if (invDeck.size() != 52) {
      return false;
    }
    // make sure that all values are unique in the deck
    Set<Card> unique = new HashSet<Card>(this.getDeck());
    return unique.containsAll(invDeck) && invDeck.containsAll(unique);
  }

  /**
   * Check the List of cards to make sure that there are no
   * duplicate cards in the deck.
   *
   * @param dummyList A list of Cards
   * @return boolean value, for if there are dupes in the list of cards
   */
  private boolean getDupes(List<Card> dummyList) {
    Set<Card> unique = new HashSet<Card>();
    for (Card c : dummyList) {
      if (!unique.add(c)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper Function to see if a card is exposed. Checks to make sure the cards below it are
   * used.
   * @param row the row of the card we are checking
   * @param card the card of the card we are checking
   * @return true if exposed, false if not
   */
  protected boolean isExposed(int row, int card) {
    return (position[row + 1][card].getCardUsed() && position[row + 1][card + 1].getCardUsed());
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

    if (row1 < 0  || row2 < 0  || card1 > getRowWidth(row1)
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
    // if row 1 is in the last, but row2 is not
    if (row1 == numRows - 1 && row2 != numRows - 1) {
      // validate that the second card is available
      if (isExposed(row2,card2)) {
        position[row1][card1].setCardUsed();
        position[row2][card2].setCardUsed();
        deck.remove(position[row1][card1]);
        deck.remove(position[row2][card2]);

        return;
      } else {
        throw new IllegalArgumentException("Card is not available");
      }
    }
    // if row 2 is in the last, but row1 is not
    if (row2 == numRows - 1 && row1 != numRows - 1) {
      // validate that the second card is available
      if (isExposed(row1,card1)) {
        position[row1][card1].setCardUsed();
        position[row2][card2].setCardUsed();
        deck.remove(position[row1][card1]);
        deck.remove(position[row2][card2]);
        return;
      } else {
        throw new IllegalArgumentException("Card is not available");
      }
    }

    //both cards are not in the last row
    if (isExposed(row1, card1) && (isExposed(row2,card2))) {
      position[row1][card1].setCardUsed();
      position[row2][card2].setCardUsed();
      deck.remove(position[row1][card1]);
      deck.remove(position[row2][card2]);
    } else {
      throw new IllegalArgumentException("Card not usable, cannot remove");
    }
  }

  /**
   * Remove a single card on the pyramid, using the specified card position.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the attempted remove is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row, int card) {
    gameStartCheck();
    // position must be in the pyramid
    // needed to change to >=
    if (row >= numRows || card >= getRowWidth(row)) {
      throw new IllegalArgumentException("Index out of range");
    }

    // Missing this in first hand-in
    if (row < 0  || card > row || card < 0) {
      throw new IllegalArgumentException("Index out of range");
    }

    // can only remove non-used cards
    if (position[row][card].getCardUsed()) {
      throw new IllegalArgumentException("Card has been used already");
    }
    // value must be 13
    if (position[row][card].getRank().getValue() != 13) {
      throw new IllegalArgumentException("Card value must be 13 to remove");
    }

    // check if in last row
    if (row == numRows - 1) {
      position[row][card].setCardUsed();
      deck.remove(position[row][card]);
      return;
    }
    // card not in last row - check to make sure cards below it have been used
    if (isExposed(row,card)) {
      position[row][card].setCardUsed();
      deck.remove(position[row][card]);
    } else {
      throw new IllegalArgumentException("Card cannot be removed");
    }
  }

  /**
   * Remove two cards, one from the draw pile and one from the pyramid.
   *
   * @param drawIndex the card from the draw pile, numbered from 0 from left
   * @param row       row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card      card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the attempted remove is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) {
    gameStartCheck();

    //position in pyramid must be valid
    // fixed card >= numRows
    if (row > numRows || card > getRowWidth(row) || row < 0 || card < 0) {
      throw new IllegalArgumentException("Index out of range");
    }
    // can only remove cards that have not been used already
    if (position[row][card].getCardUsed()) {
      throw new IllegalArgumentException("Card has been used");
    }

    if (drawIndex > numDraw - 1) {
      throw new IllegalArgumentException("invalid index beyond visible range");
    }

    if (drawIndex > stockPile.size() - 1) {
      throw new IllegalArgumentException("invalid index beyond stockpile");
    }

    if (drawIndex < 0) {
      throw new IllegalArgumentException("invalid index < 0");
    }

    Card drawnCard = stockPile.get(drawIndex);
    Card pyramidCard = position[row][card];

    // card in the stock must be visible
    if (!stockPile.get(drawIndex).getVisible()) {
      throw new IllegalArgumentException("Card not visible in draw pile");
    }
    // if in the last row
    if (row == numRows - 1) {
      if (drawnCard.getRank().getValue() + pyramidCard.getRank().getValue() != 13) {
        throw new IllegalArgumentException("The value of these two cards must add up to 13");
      }

      position[row][card].setCardUsed();
      stockPile.remove(drawnCard);

      // more stockpile cards left
      if (stockPile.size() >= numDraw) {
        Card tempCard = stockPile.get(numDraw - 1);
        stockPile.remove(numDraw - 1);
        // put the temp card where the original removed card was
        stockPile.add(drawIndex, tempCard);
        tempCard.setVisible();
        // replace removed card with the next non-visible card from stock pile
        //stockPile.get(numDraw - 1).setVisible();
        indexOfCard++;
      }
    }
    // not in the last row
    else {
      if (isExposed(row,card)) {
        // value of the drawn card and pyramid card must be 13
        // moved this down here was previously a few lines up
        if (drawnCard.getRank().getValue() + pyramidCard.getRank().getValue() != 13) {
          throw new IllegalArgumentException("The value of these two cards must add up to 13");
        }

        position[row][card].setCardUsed();
        stockPile.remove(drawnCard);

        // More stockpile cards left
        if (stockPile.size() >= numDraw) {
          Card tempCard = stockPile.get(numDraw - 1);
          stockPile.remove(numDraw - 1);
          // put the temp card where the original removed card was
          stockPile.add(drawIndex, tempCard);
          tempCard.setVisible();
          //stockPile.get(numDraw - 1).setVisible();
          indexOfCard++;
        }
      } else {
        throw new IllegalArgumentException("Card cannot be removed --> not available");
      }
    }
  }


  /**
   * Discards an individual card from the draw pile.
   *
   * @param drawIndex the card from the draw pile to be discarded
   * @throws IllegalArgumentException if the index is invalid or no card is present there.
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void discardDraw(int drawIndex) {
    gameStartCheck();
    // if the stock pile size is 0, we return
    if (stockPile.size() == 0) {
      throw new IllegalArgumentException("No Draw Cards Left");
    }
    //valid indices must be used
    if (stockPile.size() <= drawIndex || drawIndex < 0) {
      throw new IllegalArgumentException("This index is invalid");
    }
    //null index means no card there
    if (stockPile.get(drawIndex) == null) {
      throw new IllegalArgumentException("No card present there");
    }
    // drawIndex must be visible
    if (!stockPile.get(drawIndex).getVisible()) {
      throw new IllegalArgumentException("Card cannot be removed yet");
    }
    if (stockPile.size() <= numDraw) {
      stockPile.remove(drawIndex);
    }
    else {
      stockPile.remove(drawIndex);
      Card tempCard = stockPile.get(numDraw - 1);
      stockPile.remove(numDraw - 1);
      // put the temp card where the original removed card was
      stockPile.add(drawIndex, tempCard);
      tempCard.setVisible();
      //stockPile.get(numDraw - 1).setVisible();
      indexOfCard++;
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
    if (numRows < row || numRows < card) {
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
      for (int j = 0; j <= i; j++) {
        if (!(this.position[i][j].getCardUsed())) {
          scoreOfGame = scoreOfGame + position[i][j].getRank().getValue();
        }
      }
    }
    return scoreOfGame;
  }

  /**
   * Returns the number of rows originally in the pyramid, or -1 if the game hasn't been started.
   *
   * @return the height of the pyramid, or -1
   */
  @Override
  public int getNumRows() {
    if (numRows <= 0) {
      return -1;
    }
    return numRows;
  }

  /**
   * Returns the maximum number of visible cards in the draw pile, or -1 if the game hasn't been
   * started.
   *
   * @return the number of visible cards in the draw pile, or -1
   */
  @Override
  public int getNumDraw() {
    if (numRows <= 0) {
      return -1;
    }
    int countVis = 0;

    for (int i = 0; i < stockPile.size(); i++) {
      if (stockPile.get(i).getVisible()) {
        countVis++;
      }
    }
    return countVis;
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
    return row + 1;
  }

  /**
   * Returns the currently available draw cards. There should be at most {@link
   * PyramidSolitaireModel#getNumDraw} cards (the number specified when the game started) -- there
   * may be fewer, if cards have been removed.
   *
   * @return the ordered list of available draw cards
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public List<Card> getDrawCards() {
    gameStartCheck();
    List<Card> copyList = new ArrayList<Card>();

    for (int i = 0; i < getNumDraw(); i++) {
      copyList.add(stockPile.get(i));
    }
    return copyList;
  }


  /**
   * Signal if the game is over or not. A game is said to be over if there are no possible removes
   * or discards.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public boolean isGameOver() {
    System.out.println("Getting game over");
    gameStartCheck();
    //loop through the pyramid, if all values are null, that means they have been removed
    if (position.length == 0) {
      return true;
    }

    if (getScore() == 0) {
      return true;
    }


    // loop to set values playable
    // Only pyramid cards can be playable
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= i; j++) {
        if (i == numRows - 1) {
          position[i][j].setPlayable();
        }
        else if (isExposed(i,j)) {
          position[i][j].setPlayable();
        }
      }
    }

    //searching for a playable King in pyramid
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j <= i; j++) {
        if (!position[i][j].getCardUsed() && position[i][j].getPlayable()
            && position[i][j].getRank().getValue() == 13) {
          return false;
        }
      }
    }

    // Do any two playable, usable cards in the pyramid add up to 13?
    // if they do, the game is not over
    // ORIG Loop was numCardsInPyramid - but we remove cards from deck
    for (int i = 0; i < deck.size(); i++) {
      for (int j = 0; j < deck.size(); j++) {
        if (!deck.get(i).getCardUsed() && deck.get(i).getPlayable()
            && !deck.get(j).getCardUsed() && deck.get(j).getPlayable()) {
          if (deck.get(i).getRank().getValue() + deck.get(j).getRank().getValue() == 13) {
            return false;
          }
        }
      }
    }

    // Do any cards in the stock pile and the playable, non used cards in the
    // pyramid add to 13? If they do, the game is not over
    // ORIG Loop was numCardsInPyramid
    if (numDraw != 0) {
      for (int i = 0; i < deck.size(); i++) {
        for (int j = 0; j < stockPile.size(); j++) {
          if (!deck.get(i).getCardUsed() && deck.get(i).getPlayable()) {
            if (deck.get(i).getRank().getValue()
                + stockPile.get(j).getRank().getValue() == 13) {
              return false;
            }
          }
        }
      }
    }
    return !(stockPile.size() > 0 && numDraw != 0);
  }
}