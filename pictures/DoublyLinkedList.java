import java.util.Scanner;

class Node {

    int data;

    Node prev;

    Node next;

    Node(int data) {

        this.data = data;

        this.prev = null;

        this.next = null;

    }

}

public class DoublyLinkedList {

    private Node start;

    public DoublyLinkedList() {

        start = null;

    }

    public void insertAtBeginning(int val) {

        Node newNode = new Node(val);

        if (start == null) {

            start = newNode;

        } else {

            newNode.next = start;

            start.prev = newNode;

            start = newNode;

        }

    }

    public void insertAtEnd(int val) {

        Node newNode = new Node(val);

        if (start == null) {

            start = newNode;

            return;

        }

        Node ptr = start;

        while (ptr.next != null) {

            ptr = ptr.next;

        }

        ptr.next = newNode;

        newNode.prev = ptr;

    }

    public void insertAfter(int num, int val) {

        if (start == null) {

            System.out.println("List is empty.");

            return;

        }

        Node ptr = start;

        while (ptr != null && ptr.data != num) {

            ptr = ptr.next;

        }

        if (ptr == null) {

            System.out.println("Node with value " + num + " not found.");

            return;

        }

        Node newNode = new Node(val);

        newNode.next = ptr.next;

        newNode.prev = ptr;

        if (ptr.next != null) {

            ptr.next.prev = newNode;

        }

        ptr.next = newNode;

    }

    public void deleteFirst() {

        if (start == null) {

            System.out.println("UNDERFLOW");

            return;

        }

        Node ptr = start;

        start = start.next;

        if (start != null)

            start.prev = null;

        System.out.println("Deleted: " + ptr.data);

    }

    public void deleteLast() {

        if (start == null) {

            System.out.println("UNDERFLOW");

            return;

        }

        Node ptr = start;

        while (ptr.next != null) {

            ptr = ptr.next;

        }

        if (ptr.prev != null) {

            ptr.prev.next = null;

        } else {

            start = null;

        }

        System.out.println("Deleted: " + ptr.data);

    }

    public void deleteAfter(int num) {

        if (start == null) {

            System.out.println("UNDERFLOW");

            return;

        }

        Node ptr = start;

        while (ptr != null && ptr.data != num) {

            ptr = ptr.next;

        }

        if (ptr == null || ptr.next == null) {

            System.out.println("No node exists after " + num);

            return;

        }

        Node temp = ptr.next;

        ptr.next = temp.next;

        if (temp.next != null) {

            temp.next.prev = ptr;

        }

        System.out.println("Deleted: " + temp.data);

    }

    public void display() {

        if (start == null) {

            System.out.println("List is empty.");

            return;

        }

        Node ptr = start;

        System.out.print("List: ");

        while (ptr != null) {

            System.out.print(ptr.data + " ");

            ptr = ptr.next;

        }

        System.out.println();

    }

    public static void main(String[] args) {

        DoublyLinkedList dll = new DoublyLinkedList();

        Scanner sc = new Scanner(System.in);

        int choice, val = 0, num;

        do {

            System.out.println("\n--- Doubly Linked List Operations ---");

            System.out.println("1. Insert at Beginning");

            System.out.println("2. Insert at End");

            System.out.println("3. Insert After a Node");

            System.out.println("4. Delete First Node");

            System.out.println("5. Delete Last Node");

            System.out.println("6. Delete After a Node");

            System.out.println("7. Display");

            System.out.println("0. Exit");

            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter value: ");

                    dll.insertAtBeginning(val);

                    break;

                case 2:

                    System.out.print("Enter value: ");

                    val = sc.nextInt();

                    dll.insertAtEnd(val);

                    break;

                case 3:

                    System.out.print("Enter node value after which to insert: ");

                    num = sc.nextInt();

                    System.out.print("Enter value: ");

                    val = sc.nextInt();

                    dll.insertAfter(num, val);

                    break;

                case 4:

                    dll.deleteFirst();

                    break;

                case 5:

                    dll.deleteLast();

                    break;

                case 6:

                    System.out.print("Enter node value after which to delete: ");

                    num = sc.nextInt();

                    dll.deleteAfter(num);

                    break;

                case 7:

                    dll.display();

                    break;

                case 0:

                    System.out.println("Exiting...");

                    break;

                default:

                    System.out.println("Invalid choice!");

            }

        } while (choice != 0);

        sc.close();

    }

}