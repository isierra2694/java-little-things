// Towers of Hanoi - this program will solve the Hanoi puzzle recursively.

import java.util.Scanner;
import java.util.Stack;

public class TowersOfHanoi {
    // properties to keep track of columns as moves progress
    private static Stack<Integer> column1;
    private static Stack<Integer> column2;
    private static Stack<Integer> column3;
    private static int diskCount;

    public static void main(String[] args) {
        // prompt user for disk count
        Scanner input = new Scanner(System.in);
        int n;
        do {
            System.out.print("Enter a number of disks: ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            }
            n = input.nextInt();
        }
        while (n <= 0);
        // initialize properties
        diskCount = n;
        column1 = new Stack<Integer>();
        column2 = new Stack<Integer>();
        column3 = new Stack<Integer>();
        // new line
        System.out.println("\n");
        // push disks into first rod
        for (int i = n; i >= 1; i--) {
            column1.push(i);
        }
        // print initial row
        for (int i = diskCount - 1; i >= 0; i--) {
            printRow(i);
        }
        // start
        playMove(n, 1, 2, 3);
    }

    // This method will solve the entire Hanoi puzzle by calling itself until it is solved.
    private static void playMove(int n, int from, int middle, int to) {
        // if disk is 1 then return
        if (n == 1) {
            System.out.println("Take disk 1 from rod " + from + " to rod " + to + "\n");
            trackMove(1, from, to);
            return;
        }

        playMove(n-1, from, to, middle);
        System.out.println("Take disk " + n + " from rod " + from + " to rod " + to + "\n");
        trackMove(n, from, to);
        playMove(n-1, middle, from, to);
    } // end of playMove

    // Prints out the representation of the last move to the console.
    private static void trackMove(int disk, int from, int to) {
        // pop disks from the from rod
        switch (from) {
            case 1:
                disk = column1.pop();
                break;
            case 2:
                disk = column2.pop();
                break;
            case 3:
                disk = column3.pop();
                break;
        }
        // insert disks on the to rod
        switch (to) {
            case 1:
                column1.push(disk);
                break;
            case 2:
                column2.push(disk);
                break;
            case 3:
                column3.push(disk);
                break;
        }
        // print the whole move
        for (int i = diskCount - 1; i >= 0; i--) {
            printRow(i);
        }
        System.out.println("-----------------\n");
    } // end of trackMove
    
    // Prints out the individual "rows"
    private static void printRow(int r) {
        // i know we haven't gone over ternary yet but i used it anyway because it saves space
        // (js really rubs off on me)
        String cell1 = column1.size() > r ? column1.get(r).toString() : "|";
        String cell2 = column2.size() > r ? column2.get(r).toString() : "|";
        String cell3 = column3.size() > r ? column3.get(r).toString() : "|";
        System.out.println(cell1 + "\t" + cell2 + "\t" + cell3);
    } // end of printRow
}