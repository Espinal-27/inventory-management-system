package com.espinal.inventory;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {

        Database.createTable();

        boolean running = true;

        while (running) {

            showMenu();

            int option = readInt("Choose an option: ");

            switch (option) {
                case 1 -> addProduct();
                case 2 -> showProducts();
                case 3 -> searchProduct();
                case 4 -> updateProduct();
                case 5 -> deleteProduct();
                case 6 -> updateStock();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting application...");
                }
                default -> System.out.println("Invalid option.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void showMenu() {

        System.out.println("=================================");
        System.out.println("     INVENTORY MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Add product");
        System.out.println("2. View products");
        System.out.println("3. Search product");
        System.out.println("4. Update product");
        System.out.println("5. Delete product");
        System.out.println("6. Update stock");
        System.out.println("0. Exit");
        System.out.println("=================================");
    }

    private static void addProduct() {

        System.out.println("\n--- Add Product ---");

        String name = readString("Product name: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock: ");

        if (price < 0 || stock < 0) {
            System.out.println("Price and stock cannot be negative.");
            return;
        }

        Product product = new Product(name, price, stock);

        productDAO.addProduct(product);
    }

    private static void showProducts() {

        System.out.println("\n--- Product List ---");

        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.printf(
                "%-5s %-25s %-12s %-10s%n",
                "ID",
                "Name",
                "Price",
                "Stock"
        );

        System.out.println("------------------------------------------------------");

        for (Product product : products) {

            System.out.printf(
                    "%-5d %-25s $%-11.2f %-10d%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
            );
        }
    }

    private static void searchProduct() {

        System.out.println("\n--- Search Product ---");

        int id = readInt("Product ID: ");

        Product product = productDAO.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.printf("Price: $%.2f%n", product.getPrice());
        System.out.println("Stock: " + product.getStock());
    }

    private static void updateProduct() {

        System.out.println("\n--- Update Product ---");

        int id = readInt("Product ID: ");

        Product product = productDAO.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        String name = readString("New product name: ");
        double price = readDouble("New price: ");
        int stock = readInt("New stock: ");

        if (price < 0 || stock < 0) {
            System.out.println("Price and stock cannot be negative.");
            return;
        }

        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        productDAO.updateProduct(product);
    }

    private static void deleteProduct() {

        System.out.println("\n--- Delete Product ---");

        int id = readInt("Product ID: ");

        productDAO.deleteProduct(id);
    }

    private static void updateStock() {

        System.out.println("\n--- Update Stock ---");

        int id = readInt("Product ID: ");
        int newStock = readInt("New stock: ");

        if (newStock < 0) {
            System.out.println("Stock cannot be negative.");
            return;
        }

        productDAO.updateStock(id, newStock);
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid price.");
            }
        }
    }
}
