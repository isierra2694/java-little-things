// This program is an all-in-one wordsearch generator.  It comes with a bunch of features, including
// the ability to generate wordsearches from new words you input into the console, printing out your
// wordsearch to the console, showing the solution to your wordsearch, generating a wordsearch from
// a .txt file, and saving your wordsearch to a .txt file.

import java.util.*;
import java.io.*;

public class Wordsearch {
    private static Scanner input;
    private static char[][] solution;
    private static int width;
    private static boolean generated;

    public static void main(String[] args) throws FileNotFoundException {
        // initialize fields
        input = new Scanner(System.in);

        boolean keepGoing;
        do {
            keepGoing = printIntro();
        }
        while (keepGoing); // end do while
    }

    // Prints the intro for the program.
    private static boolean printIntro() throws FileNotFoundException {
        int option;
        // print out intro
        do {
            System.out.println("Welcome to my word search generator!");
            System.out.println("This program will allow you to generate your own word search puzzle.");
            System.out.println("1: Generate a new word search");
            System.out.println("2: Print out your word search");
            System.out.println("3: Show the solution to your word search");
            System.out.println("4: Generate a new word search from a list of words");
            System.out.println("5: Print your word search to a new file");
            System.out.println("6: Quit the program");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while loop
            option = input.nextInt();
        }
        while (option > 9 || option < 1); // end do while
        // command selection
        switch (option) {
            case 1:
                promptWords();
                break;
            case 2:
                print();
                break;
            case 3:
                showSolution();
                break;
            case 4:
                generateFromFile();
                break;
            case 5:
                printToFile();
                break;
            case 6:
                System.out.println("Goodbye!");
                return false;
        }

        return true;
    } // end printIntro

    // Prompts words for a new word search.
    private static void promptWords() {
        // This HAS to be 'q' only in case user enters something like 'question'
        System.out.println("Please enter 'q' when done."); 
        ArrayList<String> words = new ArrayList<String>();
        String newWord;
        // prompt for words until user enters 'q'
        do {
            System.out.print("New word: ");
            newWord = input.next();
            newWord = newWord.toUpperCase();
            words.add(newWord);
        }
        while(!newWord.equals("Q")); // end do while
        words.remove(words.size() - 1); // remove q from word list
        // calculate the wordsearch width
        int greatestLength = 0; // greatest length of word list
        for (String word: words) {
            if (word.length() > greatestLength) greatestLength = word.length();
        }
        if (greatestLength >= words.size()) {
            solution = new char[greatestLength * 2][greatestLength * 2];
            width = greatestLength * 2;
        }
        else {
            solution = new char[words.size() * 2][words.size() * 2];
            width = words.size() * 2;
        }
        // generate with word list
        generate(words);
    } // end promptWords

    // Generates the actual word search.
    private static void generate(ArrayList<String> words) {
        Random random = new Random();
        
        for (String word : words) {
            // these fields are used to calculate word placement
            int direction = 0;
            int row = 0;
            int col = 0;
            int attempts = 0;
            boolean isPlaced = false;
            while (!isPlaced && attempts <= 100) {
                isPlaced = true;
                direction = random.nextInt(4);
                row = random.nextInt(width);
                col = random.nextInt(width);
                // for every char in word
                for (int i = 0; i < word.length(); i++) {
                    int r = row;
                    int c = col;
                    switch (direction) {
                        // horizontal
                        case 0: {
                            r += i;
                            break;
                        }
                        // vertical
                        case 1: {
                            c += i;
                            break;
                        }
                        // diagonal +x
                        case 2: {
                            r += i;
                            c += i;
                            break;
                        }
                        // diagonal -x
                        case 3: {
                            r += i;
                            c -= i;
                            break;
                        }
                    }
                    // check if we are above width limit
                    if (r >= width || c >= width || r < 0 || c < 0) {
                        isPlaced = false;
                        break;
                    }
                    // check if the space is occupied (THIS IS ALSO MY IMPLEMENTATION FOR OVERLAPPING)
                    if (solution[r][c] != '\0' && solution[r][c] != word.charAt(i)) {
                        isPlaced = false;
                        break;
                    }
                }
                // if this word can be placed at the spot we determined, place it
                if (isPlaced) {
                    for (int i = 0; i < word.length(); i++) {
                        int r = row;
                        int c = col;
                        switch (direction) {
                            case 0: {
                                r += i;
                                break;
                            }
                            case 1: {
                                c += i;
                                break;
                            }
                            case 2: {
                                r += i;
                                c += i;
                                break;
                            }
                            case 3: {
                                r += i;
                                c -= i;
                                break;
                            }
                        }
                        solution[r][c] = word.charAt(i);
                    }
                    generated = true;
                }
                // add to attempts
                attempts++;
            } // end while loop
        } // end for loop
    } // end generate

    // Generates a new word search from a file.
    private static void generateFromFile() throws FileNotFoundException {
        System.out.println("Words that are written as a list in a .txt file can be used to generate a word search.");
        String directory;
        File f;
        input.nextLine();
        do {
            System.out.println("\nEnter input file directory... ");
            String path = input.nextLine();
            
            f = new File(path);
        }
        while (!f.exists()); // end do while
        Scanner fileScanner = new Scanner(f);
        ArrayList<String> words = new ArrayList<String>();
        // get list of words from file
        while (fileScanner.hasNext()) {
            words.add(fileScanner.nextLine().toUpperCase());
        }
        int greatestLength = 0;
        // determine wordsearch size
        for (String word: words) {
            if (word.length() > greatestLength) greatestLength = word.length();
        }

        if (greatestLength >= words.size()) {
            solution = new char[greatestLength * 2][greatestLength * 2];
            width = greatestLength * 2;
        }
        else {
            solution = new char[words.size() * 2][words.size() * 2];
            width = words.size() * 2;
        }
        generate(words);
    } // end generateFromFile

    // Prints out the current word search.
    private static void print() {
        if (!generated) {
            System.out.println("No wordsearch to print!");
            promptWords();
        }
        Random random = new Random();
        char[][] puzzle = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                puzzle[i][j] = solution[i][j];
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (puzzle[i][j] == '\0') {
                    puzzle[i][j] = (char) (random.nextInt(26) + 'A');
                }
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    } // end print

    // Shows the solution for the word search that has been generated.
    private static void showSolution() {
        if (!generated) {
            System.out.println("No wordsearch to print!");
            promptWords();
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {                
                System.out.print(solution[i][j] == '\0' ? "." : solution[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    } // end showSolution

    // Prints out the wordsearch to a file.
    private static void printToFile() throws FileNotFoundException {
        if (!generated) {
            System.out.println("No wordsearch to print!");
            promptWords();
        }
        System.out.println("You can print out the word search to a file.  Enter file directory: ");
        input.nextLine();
        String directory = input.nextLine();
        PrintStream output = new PrintStream(new File(directory));

        Random random = new Random();
        char[][] puzzle = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                puzzle[i][j] = solution[i][j];
            }
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (puzzle[i][j] == '\0') {
                    puzzle[i][j] = (char) (random.nextInt(26) + 'A');
                }
                output.print(puzzle[i][j] + " ");
            }
            output.println();
        }
    } // end printToFile
}