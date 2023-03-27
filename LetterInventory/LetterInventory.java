// Harrison Sierra
// CS145
// Lab 3: Letter Inventory
// This class will make an "inventory" of letters based on a string.  There are a number of different methods below
// to help manage this inventory and collate information about it.

public class LetterInventory {
    private int[] letters;
    private int size;

    public static final int LETTER_COUNT = 26;

    // constructor
    public LetterInventory(String data) {
        letters = new int[LETTER_COUNT];
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            if (Character.isLetter(data.charAt(i))) {
                letters[data.charAt(i) - 'a']++;
                size++;
            }
        }
    } // end constructor

    // Returns how many letters there are in this inventory given letter
    public int get(char letter) {
        // check if the char is a letter, if not then throw exception
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("LetterInventory.get: " + letter + " is not a letter");
        }
        // else return how many letters there are
        return letters[Character.toLowerCase(letter) - 'a'];
    } // end get

    // Sets the amount of characters in this array equal to the arguments
    public void set(char letter, int value) {
        // check if the char is a letter or if index < 0
        if (!Character.isLetter(letter) || value < 0) {
            throw new IllegalArgumentException(letter + " " + value);
        }
        // add to size
        size += value - letters[Character.toLowerCase(letter) - 'a'];
        // set characters in array to the value
        letters[Character.toLowerCase(letter) - 'a'] = value;
    } // end set

    // Return the size of this LetterInventory
    public int size() {
        return size;
    } // end size


    // Checks if LetterInventory is empty or not
    public boolean isEmpty() {
        return size == 0;
    } // end isEmpty

    // Returns a string representation of the LetterInventory
    public String toString() {
        // initialize new string with char [
        String inv = "[";
        // for every entry in letter array
        for (int i = 0; i < LETTER_COUNT; i++) {
            // for every letter in index
            for (int j = 0; j < letters[i]; j++) {
                inv += (char)('a' + i);
            } // end of for loop
        } // end of for loop

        // return string with "]" at the end
        return inv + "]";
    } // end toString

    // Create a new LetterInventory by adding another into existing
    public LetterInventory add(LetterInventory other) {
        // initialize new letterinventory
        LetterInventory combi = new LetterInventory("");
        // for every entry in letter array
        for (int i = 0; i < LETTER_COUNT; i++) {
            // add sums of this letter array with other letter array
            int otherCount = other.get((char) ('a' + i));
            combi.set((char) ('a' + i), this.letters[i] + otherCount);
        }

        return combi;
    } // end add

    // Create a new LetterInventory by subtracting another into existing
    public LetterInventory subtract(LetterInventory other) {
        // initialize new letterinventory
        LetterInventory combi = new LetterInventory("");
        for (int i = 0; i < LETTER_COUNT; i++) {
            if (this.letters[i] - other.get((char) ('a' + i)) < 0) {
                combi.set((char) ('a' + i), 0);
                continue;
            }
            combi.set((char) ('a' + i), this.letters[i] - other.get((char) ('a' + i)));
        }

        return combi;
    } // end subtract

    // Finds if another letter inventory is an anagram of this one (EXTRA CREDIT)
    public boolean isAnagramOf(LetterInventory other) {
        for (int i = 0; i < LETTER_COUNT; i++) {
            if (letters[i] != other.get((char) ('a' + i))) {
                return false;
            }
        }
        return true;
    } // end of isAnagramOf
}