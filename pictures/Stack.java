import java.util.Scanner;

class CharStack {
    private char[] stackArray;
    private int top;
    private int capacity;

    public CharStack(int size) {
        capacity = size;
        stackArray = new char[capacity];
        top = -1;
    }

    public void push(char ch) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow! Cannot push " + ch);
            return;
        }
        stackArray[++top] = ch;
    }

    public char pop() {
        if (top == -1) {
            System.out.println("Stack Underflow! Cannot delete");
            return '\0';
        }
        return stackArray[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void printStack() {
        if (top == -1) {
            System.out.println("(empty)");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i]);
        }
        System.out.println();
    }
}

public class Stack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "Pravin Sir is Best";
        char[] chars = input.toCharArray();

        CharStack mainStack = new CharStack(chars.length);
        CharStack undoStack = new CharStack(chars.length);

        for (char c : chars) {
            mainStack.push(c);
        }

        System.out.println("Initial stack content:");
        mainStack.printStack();

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("1 - do (delete last character)");
            System.out.println("2 - undo (restore last deleted character)");
            System.out.println("3 - exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (mainStack.isEmpty()) {
                        System.out.println("Nothing to delete!");
                    } else {
                        char deleted = mainStack.pop();
                        undoStack.push(deleted);
                        System.out.println("Deleted character: " + deleted);
                        System.out.print("Current stack: ");
                        mainStack.printStack();
                    }
                    break;
                case 2:
                    if (undoStack.isEmpty()) {
                        System.out.println("Nothing to undo!");
                    } else {
                        char restored = undoStack.pop();
                        mainStack.push(restored);
                        System.out.println("Restored character: " + restored);
                        System.out.print("Current stack: ");
                        mainStack.printStack();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
