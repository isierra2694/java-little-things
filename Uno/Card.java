/*
This is a card class.  It takes in a value (face value) and suit along with a bool isWild to create.
It has a number of handy methods for card information.
*/
public class Card {
   private String value;
   private String suit;
   private boolean isWild;
     
   // constructor
   public Card(String cardValue, String cardSuit, boolean isWild) {
      // initialize card
      this.value = cardValue;
      this.suit = cardSuit;
      this.isWild = isWild;
   } // end of constructor

   public String getValue() {
      return value;
   }

   public String getSuit() {
      return suit;
   }

   public boolean getIsWild() {
      return isWild;
   }
   
   // returns the card's values as a string
   public String toString() {
      String wildText = "";
      if (isWild) wildText = " (WILD)";
      return value + " of " + suit + wildText;
   } // end of toString
}