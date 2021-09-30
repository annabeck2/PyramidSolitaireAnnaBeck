package cs3500.pyramidsolitaire.model.hw02;

/**
 * Card class to represent an individual Card in the deck. Contains all attributes and
 * methods that a Card has. A Card has a Suit and a Rank. A Card in the pyramid can be used, or
 * playable. A Card in the stock can be visible. Each Card has a hash int assigned to it based
 * on the value of the Suit and the Rank.
 */
public class Card implements ICard {
  private final Suit suit;
  private final Rank rank;
  private boolean used = false;
  private final int hash;
  private boolean visible = false;
  private boolean playable = false;

  /**
   * Card constructor that takes in a suit and a rank.
   *
   * @param suit the Suit of the Card
   * @param rank the Rank of the Card
   * @throws IllegalArgumentException when suit is null
   */
  public Card(Suit suit, Rank rank) {
    if (suit == null) {
      throw new IllegalArgumentException("Suit cannot be null");
    }
    this.suit = suit;
    this.rank = rank;
    this.used = false;
    this.hash = (suit.getValue() * 13) + rank.getValue();
    this.visible = false;
    this.playable = false;
  }

  /**
   * HashCode method to return the hash of the card.
   *
   * @return  the hash
   */
  public int hashCode() {
    return hash;
  }

  /**
   * Equals method to check if two objects are the same thing.
   *
   * @param obj  The Obj
   * @return boolean, if the hashes are the same
   **/
  public boolean equals(Object obj) {
    if (!(obj instanceof Card)) {
      return false;
    }
    Card myCard = (Card) obj;
    return this.suit == myCard.suit && this.rank == myCard.rank;
  }


  /**
   * Gets the Rank of the card.
   *
   * @return the Rank rank
   */
  @Override
  public Rank getRank() {
    return rank;
  }

  /**
   * Gets the Suit of the Card.
   *
   * @return the Suit suit
   */
  @Override
  public Suit getSuit() {
    return suit;
  }

  /**
   * Gets boolean value for if the card is playable. A card is playable when the
   * two cards in the row under it are used. A card in the last row
   * is always playable.
   *
   * @return if the card is playable or not
   */
  @Override
  public boolean getPlayable() {
    return playable;
  }

  /**
   * Sets a card to be playable. A card can be set playable when the two cards in the row
   * under it have been used. A card in the bottom row is always playable.
   */
  @Override
  public void setPlayable() {
    this.playable = true;
  }

  /**
   * Enum for Rank, which has values 1 to 13, representing cards A through K.
   * Has a private int value, which is the value of the Rank and has a function
   * to get the value of the Rank.
   */
  public enum Rank {
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
  public enum Suit {
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
   * Sets the current card to be used. A card is set used when it is removed
   * from the pyramid.
   */
  @Override
  public void setCardUsed() {
    this.used = true;
  }

  /**
   * Gets the boolean value of the card being used. A card is used when it has been removed.
   * @return the bool value true or false
   */
  @Override
  public boolean getCardUsed() {
    return used;
  }

  /**
   * Sets the card to be visible. A card is set visible when it is in the stock pile.
   * As soon as the card is able to be drawn from the drawPile, it is set visible.
   */
  @Override
  public void setVisible() {
    this.visible = true;
  }

  /**
   * Gets the boolean value of the cards visibility, which is true if it can be drawn
   * from the draw pile, and false if it is in the stock pile.
   *
   * @return the bool value true or false
   */
  @Override
  public boolean getVisible() {
    return visible;
  }

  /**
   * This function returns the String representation of the Card, which includes
   * A Rank, represented A-K and a Suit, which is a heart, diamond, club or a spade.
   *
   * @return the String representation of the card
   */
  public String toString() {
    String suitPic = "";
    String rankOfCard = "";
    if (suit == Suit.Heart) {
      suitPic = "♥";
    } else if (suit == Suit.Diamond) {
      suitPic = "♦";
    } else if (suit == Suit.Spade) {
      suitPic = "♠";
    } else if (suit == Suit.Club) {
      suitPic = "♣";
    } else {
      suitPic = "";
    }

    if (rank == Rank.Ace) {
      rankOfCard = "A";
    }
    if (rank == Rank.Two) {
      rankOfCard = "2";
    }
    if (rank == Rank.Three) {
      rankOfCard = "3";
    }
    if (rank == Rank.Four) {
      rankOfCard = "4";
    }
    if (rank == Rank.Five) {
      rankOfCard = "5";
    }
    if (rank == Rank.Six) {
      rankOfCard = "6";
    }
    if (rank == Rank.Seven) {
      rankOfCard = "7";
    }
    if (rank == Rank.Eight) {
      rankOfCard = "8";
    }
    if (rank == Rank.Nine) {
      rankOfCard = "9";
    }
    if (rank == Rank.Ten) {
      rankOfCard = "10";
    }
    if (rank == Rank.Jack) {
      rankOfCard = "J";
    }
    if (rank == Rank.Queen) {
      rankOfCard = "Q";
    }
    if (rank == Rank.King) {
      rankOfCard = "K";
    }

    return rankOfCard + "" + suitPic;
  }
}
