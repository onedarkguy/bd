import java.util.Scanner;

class Book {
    String name;
    String author;
    double price;
    int copies;

    public void readData(Scanner sc) {
        System.out.print("Enter Book Name: ");
        name = sc.next();
        System.out.print("Enter Author Name: ");
        author = sc.next();
        System.out.print("Enter Price: ");
        price = sc.nextDouble();
        System.out.print("Enter Number of Copies: ");
        copies = sc.nextInt();
    }

    public void printData() {
        System.out.println("Book Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println("Copies: " + copies + "\n");
    }
}

class Library {
    static final int SIZE = 5;
    Book[] books;

    public Library() {
        books = new Book[SIZE];
    }

    public void readBooks(Scanner sc) {
        System.out.println("Enter details of " + SIZE + " books:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            books[i] = new Book();
            books[i].readData(sc);
        }
    }

    public void printBooks() {
        System.out.println("\nDisplaying all books in the library:");
        for (int i = 0; i < SIZE; i++) {
            books[i].printData();
        }
    }

    public void searchByName(String bookName) {
        boolean found = false;
        for (int i = 0; i < SIZE; i++) {
            if (books[i].name.equals(bookName)) {
                System.out.println("Book found! Number of copies available: " + books[i].copies);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book \"" + bookName + "\" not found in the library.");
        }
    }

    public void searchByAuthor(String authorName) {
        boolean found = false;
        System.out.println("Books by author \"" + authorName + "\":");
        for (int i = 0; i < SIZE; i++) {
            if (books[i].author.equals(authorName)) {
                books[i].printData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author \"" + authorName + "\".");
        }
    }

    public void sortBooksByName() {
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - i - 1; j++) {
                if (books[j].name.compareTo(books[j + 1].name) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Books sorted by name successfully.");
    }
}

public class Library_Array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        lib.readBooks(sc);
        System.out.println("\nBefore sorting:");
        lib.printBooks();
        lib.sortBooksByName();
        System.out.println("\nAfter sorting:");
        lib.printBooks();

        System.out.print("\nEnter book name to search: ");
        String searchName = sc.next();
        lib.searchByName(searchName);

        System.out.print("\nEnter author name to search: ");
        String searchAuthor = sc.next();
        lib.searchByAuthor(searchAuthor);

        sc.close();
    }
}
