// PhonebookManager is the LinkedList component for this project.  It includes a number of different methods to keep data records
// of individuals in a system.

public class PhonebookManager {
    private ListNode head;
    
    // Insert a new ListNode at the end of the list.
    public void insert(String name, String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(name, address, city, phoneNumber);
        if (this.head == null) {
            this.head = newNode;
        }
        else {
            ListNode currNode = head;
            while (currNode.next != null) {
                currNode = currNode.next;
            } // end while
            currNode.next = newNode;
        }
    } // end insert

    // Insert a new node at the beginning of the list.
    public void insertAtHead(String name, String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(name, address, city, phoneNumber);
        newNode.next = this.head;
        this.head = newNode;
    } // end insertAtHead

    // Inset a new node at any given index.
    public void insertAt(int index, String name, String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(name, address, city, phoneNumber);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode nodeRef = head;
        if (index == 0) {
            head = newNode;
            head.next = nodeRef;
        }
        // if index is bad then catch and say index is bad (extra credit??)
        try {
            for (int i = 0; i < index - 1; i++) {
                nodeRef = nodeRef.next;
            }
            newNode.next = nodeRef.next;
            nodeRef.next = newNode;
        }
        catch (NullPointerException e) {
            System.out.println("Exception in PhoneBookManager: index is out of range");
            throw e;
        }
        
    } // end insertAt

    // Delete a node at any given index.
    public void delete(int index) {
        ListNode nodeRef = head;
        if (index == 0) {
            head = head.next;
            return;
        }
        for (int i = 0; i < index - 1; i++) {
            nodeRef = nodeRef.next;
        } // end for loop
        nodeRef.next = nodeRef.next.next;
    } // end delete

    // Modify a node at any given index.
    public void modifyAt(int index, String name, String address, String city, String phoneNumber) {
        ListNode nodeRef = head;
        for (int i = 0; i < index; i++) {
            nodeRef = nodeRef.next;
        }

        if (name != null) nodeRef.name = name;
        if (address != null) nodeRef.address = address;
        if (city != null) nodeRef.city = city;
        if (phoneNumber != null) nodeRef.phoneNumber = phoneNumber;
    } // end modifyAt

    // Search the list for a name and print it if it exists.
    public void searchName(String name) {
        int count = 0;
        
        if (head != null) {
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                if (nodeRef.name.equals(name)) {
                    printSingleContact(nodeRef);
                    count++;
                }
                nodeRef = nodeRef.next;
            } // end while loop
            if (nodeRef.name.equals(name)) {
                printSingleContact(nodeRef);
                count++;
            }
        }
        System.out.println("\nThere were " + count + " matches.\n");
    } // end searchName

    // Search the list for a name and print it if it exists.
    public void searchAddress(String address) {
        int count = 0;
        if (head != null) {
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                if (nodeRef.address.equals(address)) {
                    printSingleContact(nodeRef);
                    count++;
                }
                nodeRef = nodeRef.next;
            } // end while loop
            if (nodeRef.address.equals(address)) {
                printSingleContact(nodeRef);
                count++;
            }
        }
        System.out.println("\nThere were " + count + " matches.\n");
    } // end searchAddress

    // Search the list for a name and print it if it exists.
    public void searchCity(String city) {
        int count = 0;
        if (head != null) {
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                if (nodeRef.city.equals(city)) {
                    printSingleContact(nodeRef);
                    count++;
                }
                nodeRef = nodeRef.next;
            } // end while loop
            if (nodeRef.city.equals(city)) {
                printSingleContact(nodeRef);
                count++;
            }
        }
        System.out.println("\nThere were " + count + " matches.\n");
    } // end searchCity

    // Search the list for a name and print it if it exists.
    public void searchPhoneNumber(String phoneNumber) {
        int count = 0;
        if (head != null) {
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                if (nodeRef.phoneNumber.equals(phoneNumber)) {
                    printSingleContact(nodeRef);
                    count++;
                }
                nodeRef = nodeRef.next;
            } // end while loop
            if (nodeRef.phoneNumber.equals(phoneNumber)) {
                printSingleContact(nodeRef);
                count++;
            }
        }
        System.out.println("\nThere were " + count + " matches.\n");
    } // end searchPhoneNumber

    // Print a single contact.
    private void printSingleContact(ListNode nodeRef) {
        System.out.println("---- CONTACT ----");
        System.out.println("Name: " + nodeRef.name);
        System.out.println("Address: " + nodeRef.address);
        System.out.println("City: " + nodeRef.city);
        System.out.println("Phone number: " + nodeRef.phoneNumber);
    } // end printSingleContact

    // Traverse through the list and print out all data.
    public void printList() {
        if (head != null) {
            System.out.println("---- BEGIN RECORD ----");
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                printSingleContact(nodeRef);
                nodeRef = nodeRef.next;
            } // end while loop
            printSingleContact(nodeRef);
        }
    } // end printList

    // return list count (extra credit?)
    public int getListCount() {
        int count = 0;
        if (head != null) {
            count++;
            ListNode nodeRef = head;
            while (nodeRef.next != null) {
                count++;
                nodeRef = nodeRef.next;
            } // end while loop
        }
        return count;
    } // end getListCount
}
