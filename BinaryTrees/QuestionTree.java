// This program creates a "20 questions" style game with a binary tree.
// There are several public methods that can be interacted with UserInterface
// interfaces.

import java.util.*;
import java.io.*;

public class QuestionTree {
    private UserInterface ui;
    private QuestionNode root;
    private int totalGames;
    private int gamesWon;

    // constructor
    public QuestionTree(UserInterface ui) {
        this.ui = ui;
        this.root = new QuestionNode("computer");
        totalGames = 0;
        gamesWon = 0;
    } // end QuestionTree

    // public play method (starts recursive play method)
    public void play() {
        root = play(root);
        totalGames++;
    } // end play

    // Private play method does the recursion magic for the game
    private QuestionNode play(QuestionNode current) {
        // if this is a branch then traverse left and then the right branches
        if (!isLeaf(current)) {
            ui.println(current.data);
            if (ui.nextBoolean()) {
                current.trueNode = play(current.trueNode);
            }
            else {
                current.falseNode = play(current.falseNode);
            }
        }
        // if this is a leaf then ask user if we got it right
        else {
            ui.println("Would your object happen to be a " + current.data + " (y/n)");
            if (ui.nextBoolean()) {
                ui.println("I win!");
                gamesWon++;
            }
            // if we got it wrong then prompt for a new question
            else {
                ui.println("I lose.  What is your object? ");
                String actualObject = ui.nextLine();
                ui.println("Type a yes/no question to distinguish your item from " + current.data + ":");
                String newQuestion = ui.nextLine();
                ui.println("And what is the answer for your object? ");
                // replace current node with the new question and set up brances for that node
                if (ui.nextBoolean()) {
                    current = new QuestionNode(newQuestion, new QuestionNode(actualObject), current);
                }
                else {
                    current = new QuestionNode(newQuestion, current, new QuestionNode(actualObject));
                }
            }
        }
        return current;
    } // end play

    // Public save method (starts recursive save method)
    public void save(PrintStream output) throws IllegalArgumentException { 
        if (output == null) throw new IllegalArgumentException();
        save(output, root);
    } // end save

    // Private save method does the recursion magic for saving outputs
    private void save(PrintStream output, QuestionNode current) {
        if (!isLeaf(current)) {
            output.println("Q:" + current.data);
            save(output, current.trueNode);
            save(output, current.falseNode);
        }
        else {
            output.println("A:" + current.data);
        }
    } // end save

    // Public load method (starts recursive loadLine method for each line)
    public void load(Scanner input) throws IllegalArgumentException {
        if (input == null) throw new IllegalArgumentException();
        while (input.hasNextLine()) {
            root = loadLine(input);
        }
    } // end load

    // Loads in each branch of the tree
    private QuestionNode loadLine(Scanner input) {
        String newLine = input.nextLine();
        QuestionNode root = new QuestionNode(newLine);
        if (newLine.contains("Q:")) {
            root.trueNode = loadLine(input);
            root.falseNode = loadLine(input);
        }
        root.data = newLine.substring(2, newLine.length());
        return root;
    } // end loadLine

    // Returns total games
    public int totalGames() {
        return totalGames;
    } // end totalGames

    // Returns gamesWon
    public int gamesWon() {
        return gamesWon;
    } // end gamesWon

    // Determines if a node is a leaf
    private boolean isLeaf(QuestionNode node) {
        return (node.trueNode == null || node.falseNode == null);
    } // end isLeaf
}
