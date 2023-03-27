/*
This is a DeckOfCards class.  It will generate a deck of 52 cards as an ArrayList given a number for a wild card.
It has handy methods like shuffle to shuffle the deck and getDeck to return the deck.
*/

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
   private Random random;
   private ArrayList<Card> deck;

   // constructor
   public DeckOfCards(int wildCard) {
      random = new Random();
      deck = new ArrayList<Card>();
      // values and suits arrays
      String[] values = {"One", "Two", "Three", "Four", "Five", "Six", "Seven"
      , "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
      String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
      
      // make the deck
      for (int i = 0; i < 52; i++) {
         boolean isWild = false;
         if (i % 13 == wildCard - 1) isWild = true;
         deck.add(new Card(values[i % 13], suits[i / 13], isWild));
      } // end of for loop
   } // end of constructor

   public void shuffle() {
      // for each Card, pick another random Card (0-51) and swap them 
      for (int first = 0; first < deck.size(); first++) {
         // select a random number between 0 and 51
         int second = random.nextInt(52);
         // swap current Card with randomly selected Card
         Card temp = deck.get(first);
         deck.set(first, deck.get(second));
         deck.set(second, temp);
      } // end for loop 
   }

   public ArrayList<Card> getDeck() {
      return deck;
   }
}