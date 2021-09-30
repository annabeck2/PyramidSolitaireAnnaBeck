Changes from Assignment 2 to Assignment 3

Made some Card attributes final

In BasicPyramidSolitaire

- implemented the ability to reset game -> created a new constructor that
  allows for resetGame to be called. The resetGame function initializes
  all variables back to default

- changed my getDeck method to just create a new deck, regardless if the 
  game has been started or not, due to reset game

- added check to make sure that the indices of the cards were not out
  of range in first remove function

- changed the second remove function to ensure that the card position was
  in the pyramid and to check indices

- changed removeUsingDraw to check indices of pyramid cards, cleaned up
  function to avoid duplication of error checking, changed method of replacing
  cards removed from the stock pile

- changed method of replacing cards removed from the stock pile - discardDraw

- cleaned up isGameOver -> avoids duplication of code, need to loop through 
  size of stockPile for one instead, was causing an error.

PyramidSolitaireTextualView
- added assignment 3 requirements
- changed how I inititalized values


