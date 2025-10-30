// Program: Library Book Management using Linear and Binary Search (Menu-driven)

// Course: Data Structures and Algorithms - Exp 9


import java.util.*;


class Book {

    String name;

    String author;

    double price;

    int copies;


    // Constructor

    Book(String name, String author, double price, int copies) {

        this.name = name;

        this.author = author;

        this.price = price;

        this.copies = copies;

    }


    // Display function

    void display() {

        System.out.println("Name: " + name + ", Author: " + author + ", Price: " + price + ", Copies: " + copies);

    }

}


public class Library_LinearBinarySearch {

    static Scanner sc = new Scanner(System.in);

    static Book[] books = new Book[5];

    static int count = 0; // to track number of books inserted


    // Function to insert books

    static void insertBooks() {

        if (count >= 5) {

            System.out.println("\nYou have already entered 5 books. Cannot add more!");

            return;

        }


        System.out.println("\nEnter details for Book " + (count + 1));

        System.out.print("Name: ");

        String name = sc.nextLine();

        System.out.print("Author: ");

        String author = sc.nextLine();

        System.out.print("Price: ");

        double price = sc.nextDouble();

        System.out.print("No. of copies: ");

        int copies = sc.nextInt();

        sc.nextLine(); // consume newline


        books[count] = new Book(name, author, price, copies);

        count++;


        System.out.println("Book added successfully!");

    }


    // Display all books

    static void displayBooks() {

        if (count == 0) {

            System.out.println("\nNo books available to display!");

            return;

        }


        System.out.println("\nLibrary Book Details:");

        for (int i = 0; i < count; i++) {

            books[i].display();

        }

    }


    // Linear Search

    static void linearSearch(String searchName) {

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (books[i].name.equalsIgnoreCase(searchName)) {

                System.out.println("\nBook found (Linear Search)!");

                System.out.println("No. of copies available: " + books[i].copies);

                found = true;

                break;

            }

        }

        if (!found) {

            System.out.println("\nBook not found (Linear Search).");

        }

    }


    // Binary Search (requires sorted array)

    static void binarySearch(String searchName) {

        if (count == 0) {

            System.out.println("\nNo books available to search!");

            return;

        }


        // Copy only inserted books into temp array for sorting

        Book[] sortedBooks = Arrays.copyOf(books, count);

        Arrays.sort(sortedBooks, Comparator.comparing(b -> b.name.toLowerCase()));


        int left = 0, right = count - 1;

        boolean found = false;


        while (left <= right) {

            int mid = (left + right) / 2;

            int cmp = searchName.compareToIgnoreCase(sortedBooks[mid].name);


            if (cmp == 0) {

                System.out.println("\nBook found (Binary Search)!");

                System.out.println("No. of copies available: " + sortedBooks[mid].copies);

                found = true;

                break;

            } else if (cmp < 0) {

                right = mid - 1;

            } else {

                left = mid + 1;

            }

        }


        if (!found) {

            System.out.println("\nBook not found (Binary Search).");

        }

    }


    // Menu-driven main

    public static void main(String[] args) {

        int choice;


        do {

            System.out.println("\n===== LIBRARY MENU =====");

            System.out.println("1. Insert Book");

            System.out.println("2. Display Books");

            System.out.println("3. Search Book");

            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            sc.nextLine(); // consume newline


            switch (choice) {

                case 1:

                    insertBooks();

                    break;


                case 2:

                    displayBooks();

                    break;


                case 3:

                    if (count == 0) {

                        System.out.println("\nNo books available to search!");

                        break;

                    }

                    System.out.print("\nEnter book name to search: ");

                    String searchName = sc.nextLine();


                    System.out.println("Choose search method:");

                    System.out.println("1. Linear Search");

                    System.out.println("2. Binary Search");

                    System.out.print("Enter your choice: ");

                    int searchChoice = sc.nextInt();

                    sc.nextLine(); // consume newline


                    if (searchChoice == 1)

                        linearSearch(searchName);

                    else if (searchChoice == 2)

                        binarySearch(searchName);

                    else

                        System.out.println("Invalid search option!");

                    break;


                case 4:

                    System.out.println("\nExiting... Thank you!");

                    break;


                default:

                    System.out.println("Invalid choice! Please try again.");

            }

        } while (choice != 4);

    }

}