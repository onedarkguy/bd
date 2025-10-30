import java.util.Scanner;

/**
 * Book class (C++ struct Book ki jagah)
 * Ismein book ki details store hoti hain [cite: 15-19].
 */
class Book {
    String name;
    String author;
    double price;
    int copies;

    /**
     * User se book ki details read karta hai[cite: 21].
     * @param sc Scanner object input lene ke liye
     */
    public void readData(Scanner sc) {
        System.out.print("Enter Book Name: ");
        name = sc.next(); // C++ mein 'cin >> name' jaisa [cite: 48]
        System.out.print("Enter Author Name: ");
        author = sc.next(); // C++ mein 'cin >> author' jaisa [cite: 50]
        System.out.print("Enter Price: ");
        price = sc.nextDouble(); // C++ mein 'cin >> price' jaisa [cite: 52]
        System.out.print("Enter Number of Copies: ");
        copies = sc.nextInt(); // C++ mein 'cin >> copies' jaisa [cite: 54]
    }

    /**
     * Book ki details console par print karta hai[cite: 22].
     */
    public void printData() {
        System.out.println("Book Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println("Copies: " + copies + "\n");
    }
}

/**
 * Library class (C++ struct Library ki jagah)
 * Yeh 5 books ki array ko manage karti hai [cite: 66-69].
 */
class Library {
    static final int SIZE = 5; // C++ mein 'static const int SIZE = 5' [cite: 67-68]
    Book[] books; // Book array

    // Constructor
    public Library() {
        books = new Book[SIZE]; // Array ko initialize karna
    }

    /**
     * 5 books ki details read karta hai [cite: 70-80].
     * @param sc Scanner object
     */
    public void readBooks(Scanner sc) {
        System.out.println("Enter details of " + SIZE + " books:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            books[i] = new Book(); // Har book object ko initialize karna zaroori hai
            books[i].readData(sc);
        }
    }

    /**
     * Saari books ki details print karta hai [cite: 81-86].
     */
    public void printBooks() {
        System.out.println("\nDisplaying all books in the library:");
        for (int i = 0; i < SIZE; i++) {
            books[i].printData();
        }
    }

    /**
     * Book ko naam se search karta hai [cite: 23, 87-101].
     * @param bookName Search karne ke liye book ka naam
     */
    public void searchByName(String bookName) {
        boolean found = false;
        for (int i = 0; i < SIZE; i++) {
            // Java mein string comparison .equals() se hota hai
            if (books[i].name.equals(bookName)) { // [cite: 90]
                System.out.println("Book found! Number of copies available: " + books[i].copies);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book \"" + bookName + "\" not found in the library.");
        }
    }

    /**
     * Book ko author ke naam se search karta hai [cite: 24, 102-113].
     * @param authorName Search karne ke liye author ka naam
     */
    public void searchByAuthor(String authorName) {
        boolean found = false;
        System.out.println("Books by author \"" + authorName + "\":");
        for (int i = 0; i < SIZE; i++) {
            if (books[i].author.equals(authorName)) { // [cite: 105]
                books[i].printData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author \"" + authorName + "\".");
        }
    }

    /**
     * Books ko naam ke hisab se sort karta hai (Bubble Sort) [cite: 25, 114-127].
     */
    public void sortBooksByName() {
        // C++ code  se Bubble Sort ka direct translation
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - i - 1; j++) {
                // C++ mein 'books[j].name > books[j+1].name' [cite: 121]
                // Java mein .compareTo() use hota hai
                if (books[j].name.compareTo(books[j + 1].name) > 0) {
                    // C++ mein 'swap(books[j], books[j+1])' [cite: 122]
                    // Java mein manual swap
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Books sorted by name successfully.");
    }
}

/**
 * Main class jahan se program run hoga
 */
public class Library_Array {
    public static void main(String[] args) {
        // C++ main() function ka logic yahan hai [cite: 129-146]
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        lib.readBooks(sc); // [cite: 132]

        System.out.println("\nBefore sorting:"); // [cite: 133, 135]
        lib.printBooks(); // [cite: 134]

        lib.sortBooksByName(); // [cite: 136]

        System.out.println("\nAfter sorting: "); // [cite: 137]
        lib.printBooks(); // [cite: 138]

        System.out.print("\nEnter book name to search: "); // [cite: 140]
        String searchName = sc.next();
        lib.searchByName(searchName); // [cite: 142]

        System.out.print("\nEnter author name to search: "); // [cite: 143]
        String searchAuthor = sc.next();
        lib.searchByAuthor(searchAuthor); // [cite: 145]

        sc.close(); // Scanner ko close karna
    }
}