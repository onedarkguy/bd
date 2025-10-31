import java.util.*;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - Rs." + price;
    }
}

public class ShoppingCart_Stack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        java.util.Stack<Product> cart = new java.util.Stack<>();

        double priceLimit = 1000.0;
        int choice;

        do {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Last Product");
            System.out.println("3. View Cart");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter product price: ");
                    double price = sc.nextDouble();

                    if (price > priceLimit) {
                        System.out
                                .println("Product price exceeds limit (Rs." + priceLimit + "). Cannot add to cart.");
                    } else {
                        cart.push(new Product(name, price));
                        System.out.println("Product added to cart.");
                    }
                    break;

                case 2:
                    if (!cart.isEmpty()) {
                        Product removed = cart.pop();
                        System.out.println("Removed: " + removed);
                    } else {
                        System.out.println("Cart is empty. Nothing to remove.");
                    }
                    break;

                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("\n--- Your Cart (Top = Last Added) ---");
                        for (int i = cart.size() - 1; i >= 0; i--) {
                            System.out.println(cart.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Thank you for shopping!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
