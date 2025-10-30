import java.util.Scanner;


// TreeNode Class: Represents a node in the Binary Search Tree

class ProductNode {

    int productId;

    String productName;

    int productStock;

    ProductNode left, right;


    public ProductNode(int productId, String productName, int productStock) {

        this.productId = productId;

        this.productName = productName;

        this.productStock = productStock;

        this.left = this.right = null;

    }

}


// Binary Search Tree Class

class ProductBST {

    ProductNode root;


    public ProductBST() {

        root = null;

    }


    // Insert a product into the BST (arranged by productId)

    public void insert(int id, String name, int stock) {

        root = insertNode(root, id, name, stock);

        System.out.println("Product inserted successfully.");

        displayTree();

    }


    private ProductNode insertNode(ProductNode root, int id, String name, int stock) {

        if (root == null) {

            return new ProductNode(id, name, stock);

        }

        if (id < root.productId) {

            root.left = insertNode(root.left, id, name, stock);

        } else if (id > root.productId) {

            root.right = insertNode(root.right, id, name, stock);

        } else {

            // If product already exists, update stock

            root.productStock += stock;

            System.out.println("Product already exists. Stock updated.");

        }

        return root;

    }


    // Search for a product

    public void search(int id) {

        ProductNode parent = null;

        ProductNode current = root;

        boolean found = false;


        while (current != null) {

            if (current.productId == id) {

                found = true;

                break;

            }

            parent = current;

            if (id < current.productId) {

                current = current.left;

            } else {

                current = current.right;

            }

        }


        if (found) {

            if (parent == null) {

                System.out.println("Product found at ROOT: " +

                    current.productId + " - " + current.productName +

                    " (Stock: " + current.productStock + ")");

            } else if (parent.left == current) {

                System.out.println("Product found at LEFT of " + parent.productId +

                    ": " + current.productId + " - " + current.productName +

                    " (Stock: " + current.productStock + ")");

            } else {

                System.out.println("Product found at RIGHT of " + parent.productId +

                    ": " + current.productId + " - " + current.productName +

                    " (Stock: " + current.productStock + ")");

            }

        } else {

            System.out.println("Product not found.");

        }


        displayTree();

    }


    // Delete a product

    public void delete(int id) {

        root = deleteNode(root, id);

        System.out.println("Product deleted successfully.");

        displayTree();

    }


    private ProductNode deleteNode(ProductNode root, int id) {

        if (root == null) {

            return root;

        }

        if (id < root.productId) {

            root.left = deleteNode(root.left, id);

        } else if (id > root.productId) {

            root.right = deleteNode(root.right, id);

        } else {

            // Found node to delete

            if (root.left == null && root.right == null) {

                return null;

            } else if (root.left == null) {

                return root.right;

            } else if (root.right == null) {

                return root.left;

            }

            // Replace with inorder successor

            ProductNode successor = minValueNode(root.right);

            root.productId = successor.productId;

            root.productName = successor.productName;

            root.productStock = successor.productStock;

            root.right = deleteNode(root.right, successor.productId);

        }

        return root;

    }


    private ProductNode minValueNode(ProductNode node) {

        while (node.left != null) {

            node = node.left;

        }

        return node;

    }


    // Display the BST in-order

    public void displayTree() {

        if (root == null) {

            System.out.println("Inventory is empty.");

        } else {

            System.out.println("\nInventory (In-order Traversal):");

            System.out.printf("%-10s %-15s %-10s%n", "ProductID", "ProductName", "Stock");

            inOrderTraversal(root);

        }

    }


    private void inOrderTraversal(ProductNode root) {

        if (root != null) {

            inOrderTraversal(root.left);

            System.out.printf("%-10d %-15s %-10d%n",

                root.productId, root.productName, root.productStock);

            inOrderTraversal(root.right);

        }

    }

}


// Main class

public class Inventory_BinarySearchTree {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductBST bst = new ProductBST();


        while (true) {

            System.out.println("\nMenu:");

            System.out.println("1. Insert a product");

            System.out.println("2. Search for a product");

            System.out.println("3. Delete a product");

            System.out.println("4. Display Inventory");

            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();


            switch (choice) {

                case 1:

                    System.out.print("Enter Product ID: ");

                    int id = scanner.nextInt();

                    scanner.nextLine(); // consume newline

                    System.out.print("Enter Product Name: ");

                    String name = scanner.nextLine();

                    System.out.print("Enter Product Stock: ");

                    int stock = scanner.nextInt();

                    bst.insert(id, name, stock);

                    break;

                case 2:

                    System.out.print("Enter Product ID to search: ");

                    bst.search(scanner.nextInt());

                    break;

                case 3:

                    System.out.print("Enter Product ID to delete: ");

                    bst.delete(scanner.nextInt());

                    break;

                case 4:

                    bst.displayTree();

                    break;

                case 5:

                    System.out.println("Exiting...");

                    scanner.close();

                    return;

                default:

                    System.out.println("Invalid choice, please try again.");

            }

        }

    }

}