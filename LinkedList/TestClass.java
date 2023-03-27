// TestClass provides an easy to use terminal interface for interacting with a PhonebookManager.

import java.util.*;

public class TestClass {
    private static Scanner input;
    private static PhonebookManager manager;

    public static void main(String[] args) {
        // initialize fields
        input = new Scanner(System.in);
        manager = new PhonebookManager();
        
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
            System.out.println("1 - Add");
            System.out.println("2 - Delete");
            System.out.println("3 - Modify");
            System.out.println("4 - Search contacts");
            System.out.println("5 - View contacts");
            System.out.println("6 - Quit");
            System.out.println("Enter a command: ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while loop
            option = input.nextInt();
        }
        while (option > 6 || option < 1);
        // end do while loop

        switch (option) {
            case 1:
                addContact();
                break;
            case 2:
                deleteContact();
                break;
            case 3:
                modifyContact();
                break;
            case 4:
                searchContacts();
                break;
            case 5:
                viewContacts();
                break;
            case 6:
                System.out.println("Goodbye!");
                return false;
        }
        return true;
    } // end promptOption

    // add in a new contact
    private static void addContact() {
        // prompt for contact info
        System.out.println("\nYou have selected to add a contact.");
        System.out.println("Please provide contact information: ");
        input.nextLine();
        System.out.println("Name: ");
        String name = input.nextLine();
        System.out.println("Address: ");
        String address = input.nextLine();
        System.out.println("City: ");
        String city = input.nextLine();
        System.out.println("Phone number: ");
        String phoneNumber = input.nextLine();

        // prompt for where they want to add it
        int option;
        do {
            System.out.println("1 - Add at beginning of contact list.");
            System.out.println("2 - Add at end of contact list.");
            System.out.println("3 - Add at a certain place in the contact list.");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while loop
            option = input.nextInt();
        }
        while (option > 3 || option < 1);
        // end do while

        switch (option) {
            // insert at beginning
            case 1:
                manager.insertAtHead(name, address, city, phoneNumber);
                System.out.println("Added to beginning of contact list.\n");
                break;
            case 2:
            // insert at end
                manager.insert(name, address, city, phoneNumber);
                System.out.println("Added to end of contact list.\n");
                break;
            // insert at index
            case 3:
                // if no items add to start
                int index;
                if (manager.getListCount() == 0) {
                    System.out.println("No items in the list, adding to index 0.");
                    manager.insertAtHead(name, address, city, phoneNumber);
                    System.out.println("Added contact.\n");
                    return;
                }
                // prompt for index
                do {
                    System.out.println("Enter the index that you'd like to add the contact: ");
                    while (!input.hasNextInt()) {
                        System.out.println("You entered an invalid option.  Please try again.");
                        input.next();
                    } // end while loop
                    index = input.nextInt();
                }
                while (manager.getListCount() - 1 < index || index < 0);
                // end do while
                manager.insertAt(index, name, address, city, phoneNumber);
                System.out.println("Added contact.\n");
        }
    } // end addContact

    // delete contact given index
    private static void deleteContact() {
        // check if there are no contacts, if none return
        System.out.println("\nYou have selected to delete a contact.");
        if (manager.getListCount() == 0) {
            System.out.println("There are no contacts in the list!\n");
            return;
        }

        // prompt user for index
        int index;
        do {
            System.out.println("Please provide the index that you'd like to delete: ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while loop
            index = input.nextInt();
        }
        while (manager.getListCount() - 1 < index || index < 0);
        // end do while

        manager.delete(index);
        System.out.println("Deleted contact.\n");
    } // end deleteContact

    // modify contact information
    private static void modifyContact() {
        // if no contacts return
        System.out.println("\nYou have selected to modify a contact.");
        if (manager.getListCount() == 0) {
            System.out.println("There are no contacts in the list!\n");
            return;
        }
        
        // prompt user for index
        int index;
        do {
            System.out.println("Please provide the index that you'd like to modify: ");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while
            index = input.nextInt();
        }
        while (manager.getListCount() - 1 < index || index < 0);
        // end do while

        // prompt user for option (name, address, city, phone no)
        int option;
        do {
            System.out.println("1 - Modify name.");
            System.out.println("2 - Modify address.");
            System.out.println("3 - Modify city.");
            System.out.println("4 - Modify phone number.");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            } // end while
            option = input.nextInt();
        }
        while (option > 4 || option < 1);
        // end do while
        
        input.nextLine();
        switch (option) {
            // name
            case 1:
                System.out.println("New name: ");
                String name = input.nextLine();
                manager.modifyAt(index, name, null, null, null);
                break;
            // address
            case 2:
                System.out.println("New address:");
                String address = input.nextLine();
                manager.modifyAt(index, null, address, null, null);
                break;
            // city
            case 3:
                System.out.println("New city: ");
                String city = input.nextLine();
                manager.modifyAt(index, null, null, city, null);
                break;
            // phone no
            case 4:
                System.out.println("New phone number: ");
                String phoneNumber = input.nextLine();
                manager.modifyAt(index, null, null, null, phoneNumber);
                break;
        }
        System.out.println("Modified contact.");
    } // end modifyContact
    
    // EXTRA CREDIT
    // search contact list
    private static void searchContacts() {
        // prompt for name, address, city, or phone no
        System.out.println("You have selected to search the contact list.");
        int option;
        do {
            System.out.println("1 - Search for name.");
            System.out.println("2 - Search for address.");
            System.out.println("3 - Search for city.");
            System.out.println("4 - Search for phone number.");
            while (!input.hasNextInt()) {
                System.out.println("You entered an invalid option.  Please try again.");
                input.next();
            }
            option = input.nextInt();
        }
        while (option > 4 || option < 1);
        
        // prompt for appropriate option then search
        input.nextLine();
        switch (option) {
            // name
            case 1:
                System.out.println("Enter name: ");
                String name = input.nextLine();
                manager.searchName(name);
                break;
            // address
            case 2:
                System.out.println("Enter address: ");
                String address = input.nextLine();
                manager.searchAddress(address);
                break;
            // city
            case 3:
                System.out.println("Enter city: ");
                String city = input.nextLine();
                manager.searchCity(city);
                break;
            // phone no
            case 4:
                System.out.println("Enter phone number: ");
                String phoneNumber = input.nextLine();
                manager.searchPhoneNumber(phoneNumber);
                break;
        }
    } // end searchContacts

    // print contact list
    private static void viewContacts() {
        System.out.println("\nYou have selected to view the contact list.");
        manager.printList();
    } // end viewContacts
}
