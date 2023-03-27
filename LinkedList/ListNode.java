// ListNode provides the LinkedList functionality.  It should only be used by the LinkedList class.

public class ListNode {
    public String name;
    public String address;
    public String city;
    public String phoneNumber;

    public ListNode next;

    public ListNode(String _name, String _address, String _city, String _phoneNumber) {
        this.name = _name;
        this.address = _address;
        this.city = _city;
        this.phoneNumber = _phoneNumber;

        this.next = null;
    }

    public ListNode(String _name, String _address, String _city, String _phoneNumber, ListNode _next) {
        this.name = _name;
        this.address = _address;
        this.city = _city;
        this.phoneNumber = _phoneNumber;

        this.next = _next;
    }
}