import java.util.Scanner;

class CharStack {
    private char[] stackArray;
    private int top;
    private int capacity;

    // Constructor
    public CharStack(int size) {
        capacity = size;
        stackArray = new char[capacity];
        top = -1; // [cite: 90]
    }

    // Push operation: Adds an item to the stack
    public void push(char ch) {
        // Corrected 'capacity 1' to 'capacity - 1' 
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot push " + ch);
            return;
        }
        stackArray[++top] = ch; // [cite: 96]
    }

    // Pop operation: Removes an item from the stack
    public char pop() {
        // Corrected 'top ==-1{' to 'top == -1' 
        if (top == -1) {
            System.out.println("Stack Underflow! Cannot delete");
            return '\0'; // [cite: 101]
        }
        return stackArray[top--]; // [cite: 103]
    }

    // Check if stack is empty
    public boolean isEmpty() {
        // Corrected 'top ==-1;' to 'top == -1' 
        return top == -1;
    }

    // Print stack contents
    public void printStack() {
        if (top == -1) {
            System.out.println("(empty)"); // [cite: 110]
            return;
        }
        // The loop logic is corrected to print on one line to match the output
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i]); // [cite: 114]
        }
        System.out.println(); // Moved outside the loop
    }
}

public class Stack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "Pravin Sir is Best";
        char[] chars = input.toCharArray(); // [cite: 121]

        CharStack mainStack = new CharStack(chars.length);
        CharStack undoStack = new CharStack(chars.length);

        // Corrected loop: The push operation was outside the loop in the file [cite: 123-125]
        for (char c : chars) {
            mainStack.push(c);
        }

        System.out.println("Initial stack content:");
        mainStack.printStack(); // [cite: 126]

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1 - do (delete last character)"); // [cite: 129-130]
            System.out.println("2 - undo (restore last deleted character)"); // [cite: 131-132]
            System.out.println("3 - exit"); // [cite: 133-134]
            int choice = sc.nextInt(); // [cite: 135]

            switch (choice) { // [cite: 136]
                case 1: // "do" (delete)
                    if (mainStack.isEmpty()) { // [cite: 138]
                        System.out.println("Nothing to delete!");
                    } else {
                        char deleted = mainStack.pop(); // [cite: 142]
                        undoStack.push(deleted); // [cite: 143]
                        System.out.println("Deleted character: " + deleted);
                        System.out.print("Current stack: ");
                        mainStack.printStack(); // [cite: 146]
                    }
                    break;
                case 2: // "undo" (restore)
                    if (undoStack.isEmpty()) { // [cite: 149]
                        System.out.println("Nothing to undo!");
                    } else {
                        char restored = undoStack.pop(); // [cite: 153]
                        mainStack.push(restored); // [cite: 154]
                        System.out.println("Restored character: " + restored);
                        System.out.print("Current stack: ");
                        mainStack.printStack(); // [cite: 157]
                    }
                    break;
                case 3: // exit
                    System.out.println("Exiting..."); // [cite: 160]
                    sc.close(); // [cite: 161]
                    return;
                default:
                    System.out.println("Invalid choice, try again."); // [cite: 164]
            }
        }
    }
}