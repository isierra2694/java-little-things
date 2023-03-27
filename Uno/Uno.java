/*
The Uno class powers the whole game using Card, DeckOfCards, and Player class.  It's pretty big.
*/

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class Uno {
    private static Scanner input;

    public static void main(String[] args) {
        introduceGame();
        input = new Scanner(System.in);
        Boolean keepGoing;
        
        do {
            keepGoing = playAgain();
        }
        while (keepGoing);
    }

    // introduce the game
    public static void introduceGame() {
        System.out.println("Welcome to UNO!");
        System.out.println("----------------");
        System.out.println("There are a few rules here: ");
        System.out.println("1. You start with 7 cards.");
        System.out.println("2. There is a draw pile and a discard pile.  You must match");
        System.out.println("either the number or the suit of the top card on the discard pile.");
        System.out.println("3. When you draw a WILD card, you can choose what suit the next player must play.");
        System.out.println("4. If you can't play a card from your deck, draw a card from the draw pile.");
        System.out.println("----------------");
    } // end of introduceGame

    // initialize new game
    public static void newGame() {
        int amountOfPlayers = 0;
        // Prompt for amount of players that are playing
        do {
            System.out.println("How many players?  Enter a number between 2-5 ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            }
            
            amountOfPlayers = input.nextInt();
        }
        while (amountOfPlayers < 2 || amountOfPlayers > 5);
        // end of do while loop
        System.out.println("Playing with " + amountOfPlayers + " players.");
        
        int selectedWild = 0;
        // Prompt for the user-selected wild card
        do {
            System.out.println("Which cards would you like to be WILD?  Enter a number between 1-10 ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            }
            selectedWild = input.nextInt();
        }
        while (selectedWild < 1 || selectedWild > 10);
        input.nextLine();

        // end of do while loop
        System.out.println("You selected the " + selectedWild + " cards as your WILD cards");

        ArrayList<Player> players = promptPlayerNames(amountOfPlayers);
        System.out.println("----------------------\n");
        startGame(players, selectedWild);
    } // end of newGame

    // start a new game
    private static void startGame(ArrayList<Player> players, int wildCard) {
        DeckOfCards newDeck = new DeckOfCards(wildCard);
        Stack<Card> drawDeck = new Stack<Card>();
        Stack<Card> discardDeck = new Stack<Card>();

        newDeck.shuffle();
        drawDeck.addAll(newDeck.getDeck());

        // Create starting decks for each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card newCard = drawDeck.pop();
                player.addToDeck(newCard);
            }
        } // end of for loop

        int playerIndex = 0;
        discardDeck.add(drawDeck.pop());
        String cachedWild = "";
        
        // Main gameplay loop
        do {
            // Get the top card
            Card topCard = discardDeck.peek();
            System.out.println("Top card: " + topCard + "\n");
            // get active player
            Player activePlayer = players.get(playerIndex);
            String playerName = activePlayer.getPlayerName();
            System.out.println("It is " + playerName + "'s turn!\n");
            // initialize player's cardToPlay
            Card cardToPlay = null;

            do {
                // refill if draw deck is empty
                if (drawDeck.empty()) {
                    System.out.println("Draw deck is empty, refilling!");
                    Card cachedTopCard = discardDeck.pop();
                    drawDeck.addAll(discardDeck);
                    discardDeck.removeAllElements();
                    discardDeck.add(cachedTopCard);
                }
                // Figure out playable cards
                System.out.println("Here is your hand, " + playerName + ":");
                System.out.println(activePlayer.deckToString() + "\n");
                ArrayList<Card> playerHand = activePlayer.getPlayerHand();
                ArrayList<Card> playableCards = new ArrayList<Card>();
                for (Card card : playerHand) {
                    if (card.getValue() == topCard.getValue() && cachedWild == "") {
                        playableCards.add(card);
                        continue;
                    }
                    else if (card.getSuit() == topCard.getSuit() && cachedWild == "") {
                        playableCards.add(card);
                        continue;
                    }
                    else if (card.getSuit() == cachedWild) {
                        playableCards.add(card);
                        continue;
                    }
                    else if (card.getIsWild()) {
                        playableCards.add(card);
                        continue;
                    }
                    else {
                        continue;
                    }
                } // end of for loop
                // if we don't have playable cards then draw and end turn cycle (but repeat)
                if (playableCards.size() == 0) {
                    System.out.println(playerName + ", you don't have any playble cards, so you draw.");
                    activePlayer.addToDeck(drawDeck.pop());
                }
                // else, allow player to enter an option
                else {
                    System.out.println(playerName + ", you have " + playableCards.size() + " options.");
                    int selectedCard;
                    do {
                        System.out.print(playerName + ", enter the card that you'd like to play (1 is the first card in your deck, 2 is the next card, etc.): ");
                        while (!input.hasNextInt()) {
                            System.out.println("You entered an invalid option.  Please try again.");
                            input.next();
                        }
                        selectedCard = input.nextInt();
                    }
                    while (selectedCard <= 0 || selectedCard > playerHand.size() || !checkIfPlayedCardIsValid(selectedCard, topCard, playerHand, cachedWild));
                    // end of do while loop
                    cardToPlay = playerHand.get(selectedCard-1);
                }
            }
            while (cardToPlay == null);
            // end of do while loop
            System.out.println(playerName + " played " + cardToPlay.toString());
            // check if card is wild, if so, prompt for an option for player to choose from
            if (cardToPlay.getIsWild()) {
                int selectedSuit;
                // prompt for suit
                do {
                    System.out.println(playerName + ", you played a WILD card.  What suit would you like to choose? 1=Diamonds, 2=Hearts, 3=Clubs, 4=Spades: ");
                    while (!input.hasNextInt()) {
                        System.out.println("You entered an invalid option.  Please try again.");
                        input.next();
                    }
                    selectedSuit = input.nextInt();
                }
                while (selectedSuit <= 0 || selectedSuit > 4);
                // end of do while
                String[] suitStrings = {"Diamonds", "Hearts", "Clubs", "Spades"};
                cachedWild = suitStrings[selectedSuit-1];
                System.out.println(playerName + ", you picked " + cachedWild + " as your option.  The next player must play a " + cachedWild + " card.");
            }
            // if not a wild card then clear cache
            else {
                cachedWild = "";
            }
            // add played card to discard deck
            discardDeck.add(cardToPlay);
            // remove played card from player deck
            activePlayer.removeFromDeck(cardToPlay);
            // add to player index
            playerIndex++;
            // return in terminal
            System.out.println("\n\n");
            // if player index reached player count reset
            if (playerIndex >= players.size()) playerIndex = 0;
        }
        while (!checkForWinner(players));
        // end of do while loop
    } // end of startGame

    // check if played card is a valid card to play
    private static Boolean checkIfPlayedCardIsValid(int cardToPlay, Card currentCard, ArrayList<Card> playerHand, String cachedWild) {
        Card pickedCard = playerHand.get(cardToPlay - 1);
        if (pickedCard.getValue() == currentCard.getValue() && cachedWild == "") {
            return true;
        }
        else if (pickedCard.getSuit() == currentCard.getSuit() && cachedWild == "") {
            return true;
        }
        else if (pickedCard.getIsWild()) {
            return true;
        }
        else if (pickedCard.getSuit() == cachedWild) {
            return true;
        }
        else {
            return false;
        }
    } // end of checkifPlayedCardIsValid

    // check for a winner given list of players
    private static Boolean checkForWinner(ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getDeckCount() == 0) {
                System.out.println(player.getPlayerName() + " won the game!\n\n");
                return true; // if we have a winner, stop game
            }
        } // end of foreach loop
        // else continue
        return false;
    } // end of checkForWinner

    // ask user for player names given amount of players
    private static ArrayList<Player> promptPlayerNames(int amountOfPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();
        // Prompt and create new players for each name
        for (int i = 1; i <= amountOfPlayers; i++) {
            System.out.println("Please enter Player " + i + "'s name: ");
            String name = input.nextLine();
            players.add(new Player(name));
        } // end of for loop
        return players;
    } // end of promptPlayerNames

    // ask if we want to play again
    private static Boolean playAgain() {
        System.out.println("Would you like to play?  (Y/N) ");
        String response = input.nextLine();

        if (response.startsWith("y")) {
            newGame();
            return true;
        }
        else if (response.startsWith("n")) {
            System.out.println("Goodbye!");
            return false;
        }
        else {
            System.out.println("You entered an invalid option.  Please try again.");
            return true;
        }
    } // end of playAgain
}
