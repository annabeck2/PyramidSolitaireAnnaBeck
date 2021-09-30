package cs3500.pyramidsolitaire.model.hw02;

/**
 * Card Interface to represent an individual Card in the deck. Contains all methods that
 * are used in a Card.
 */
public interface ICard {


  /**
   * Enum for Rank, which has values 1 to 13, representing cards A through K.
   * Has a private int value, which is the value of the Rank and has a function
   * to get the value of the Rank.
   */
  enum Rank {
    Ace(1), Two(2), Three(3), Four(4), Five(5),
    Six(6), Seven(7), Eight(8), Nine(9),
    Ten(10), Jack(11), Queen(12), King(13);

    private final int value;
    Rank(int value) {
      this.value = value;
    }

    /**
     * Gets the value of the Rank in the current card.
     *
     * @return the value of the Card
     */
    public int getValue() {
      return value;
    }
  }

  /**
   * Enum for Suit, which has suits H, D, S and C, with values 0 to 3.
   * Has a private int value, which is the value of the Suit and has a function
   * to get the value of the Suit.
   */
  enum Suit {
    Heart(0), Diamond(1), Spade(2), Club(3);

    private final int value;
    Suit(int value) {
      this.value = value;
    }

    /**
     * Getting the value of the Suit in the current card.
     *
     * @return the value of the suit
     */
    public int getValue() {
      return value;
    }

  }

  /**
   * Gets the Rank of the card.
   *
   * @return the Rank rank
   */
  Card.Rank getRank();

  /**
   * Gets the Suit of the Card.
   *
   * @return the Suit suit
   */
  Card.Suit getSuit();

  /**
   * Gets boolean value for if the card is playable. A card is playable when the
   * two cards in the row under it are used. A card in the last row
   * is always playable.
   *
   * @return if the card is playable or not
   */
  boolean getPlayable();

  /**
   * Sets a card to be playable. A card can be set playable when the two cards in the row
   * under it have been used. A card in the bottom row is always playable.
   */
  void setPlayable();

  /**
   * Sets the current card to be used. A card is set used when it is removed
   * from the pyramid.
   */
  void setCardUsed();

  /**
   * Gets the boolean value of the card being used. A card is used when it has been removed.
   * @return the bool value true or false
   */
  boolean getCardUsed();

  /**
   * Gets the boolean value of the cards visibility, which is true if it can be drawn
   * from the draw pile, and false if it is in the stock pile.
   *
   * @return the bool value true or false
   */
  boolean getVisible();

  /**
   * Sets the card to be visible. A card is set visible when it is in the stock pile.
   * As soon as the card is able to be drawn from the drawPile, it is set visible.
   */
  void setVisible();

  /**
   * This function returns the String representation of the Card, which includes
   * A Rank, represented A-K and a Suit, which is a heart, diamond, club or a spade.
   *
   * @return the String representation of the card
   */
  String toString();

}
