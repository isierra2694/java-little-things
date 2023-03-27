/*
This is a Player class.  It keeps track of the player's current hand and the player name.
*/

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> deck;
    private String name;

    // constructor
    public Player(String playerName) {
        this.name = playerName;
        deck = new ArrayList<Card>();
    } // end of constructor

    // return this player's name
    public String getPlayerName() {
        return name;
    } // end of getPlayerName

    // adds a new card to the deck
    public void addToDeck(Card newCard) {
        deck.add(newCard);
    } // end of addToDeck

    public void removeFromDeck(Card cardToRemove) {
        deck.remove(cardToRemove);
    }

    // returns the deck as a string
    public String deckToString() {
        // create new list to store card strings
        ArrayList<String> deckStringList = new ArrayList<String>();
        
        // iterate over every card in deck and add it to deckStringList
        for (Card card : deck) {
            deckStringList.add(card.toString());
        } // end of for loop

        // return the entire deck as one string
        String deckAsString = String.join(", ", deckStringList);
        return deckAsString;
    } // end of deckToString

    public ArrayList<Card> getPlayerHand() {
        return deck;
    }

    public int getDeckCount() {
        return deck.size();
    }
}
