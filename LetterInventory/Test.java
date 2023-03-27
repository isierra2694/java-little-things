import java.util.*;

public class Test {
    private static Scanner input;
    private static LetterInventory inventory;

    public static void main(String[] args) {
        // initialize fields
        input = new Scanner(System.in);

        boolean keepGoing;
        do {
            keepGoing = promptOption();
        }
        while (keepGoing);
    }

    // prompt user for new option
    private static boolean promptOption() {
        
        int option;
        do {
            System.out.println("1 - New inventory");
            System.out.println("2 - Get letter count");
            System.out.println("3 - Set letter count");
            System.out.println("4 - Get size");
            System.out.println("5 - Print inventory");
            System.out.println("6 - Add letter inventory");
            System.out.println("7 - Subtract letter inventory");
            System.out.println("8 - Check if inventory is an anagram");
            System.out.println("9 - Quit");
            System.out.println("Enter a command: ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while loop
            option = input.nextInt();
        }
        while (option > 9 || option < 1);
        // end do while loop

        switch (option) {
            case 1:
                newInventory();
                break;
            case 2:
                getLetterCount();
                break;
            case 3:
                setLetterCount();
                break;
            case 4:
                getSize();
                break;
            case 5:
                printInventory();
                break;
            case 6:
                addInventory();
                break;
            case 7:
                subtractInventory();
                break;
            case 8:
                isAnagram();
                break;
            case 9:
                System.out.println("Goodbye!");
                return false;
        }
        return true;
    } // end promptOption

    // new inventory
    private static void newInventory() {
        input.nextLine();
        System.out.println("String: ");
        String letters = input.nextLine();
        inventory = new LetterInventory(letters);
    }

    // get letter count
    private static void getLetterCount() {
        input.nextLine();
        System.out.println("Letter: ");
        String letter = input.next();
        char[] letters = letter.toCharArray();
        System.out.println(inventory.get(letters[0]));        
    } // end deleteContact

    // set letter count
    private static void setLetterCount() {
        input.nextLine();
        System.out.println("Letter: ");
        String letter = input.next();
        char[] letters = letter.toCharArray();
        
        do {
            System.out.println("New count: ");
        }
        while (input.hasNextInt());
        
        inventory.set(letters[0], input.nextInt());
    } // end of setLetterCount
    
    // gets the inventory size
    private static void getSize() {
        System.out.println(inventory.size());
    } // end of getSize

    // prints a list of all letters
    private static void printInventory() {
        System.out.println(inventory.toString());
    } // end of printInventory

    // adds a new inventory to existing
    private static void addInventory() {
        input.nextLine();
        System.out.println("String: ");
        String letters = input.nextLine();
        LetterInventory newInventory = new LetterInventory(letters);

        inventory = inventory.add(newInventory);
    } // end of addInventory

    // subtracts inventory from existing
    private static void subtractInventory() {
        input.nextLine();
        System.out.println("String: ");
        String letters = input.nextLine();
        LetterInventory newInventory = new LetterInventory(letters);

        inventory = inventory.subtract(newInventory);
    } // end of subtractInventory

    // checks if inventory is anagram
    private static void isAnagram() {
        input.nextLine();
        System.out.println("String: ");
        String letters = input.nextLine();
        LetterInventory newInventory = new LetterInventory(letters);

        Boolean isAnagram = inventory.isAnagramOf(newInventory);
        System.out.println(isAnagram);
    } // end of isAnagram
}
