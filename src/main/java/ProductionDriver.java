
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Product;

public class ProductionDriver {

    // Stores every produced item in order of creation
    private static final List<Product> productionLog = new ArrayList<>();

    // Maps product name -> count of items produced
    private static final Map<String, Integer> productCatalog = new LinkedHashMap<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("       Welcome to the Production System       ");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" ->
                    addProduct();
                case "2" ->
                    displayProductionLog();
                case "3" ->
                    displayStatistics();
                case "4" -> {
                    System.out.println("\nShutting down. Goodbye!");
                    running = false;
                }
                default ->
                    System.out.println("\n[!] Invalid option. Please enter 1–4.\n");
            }
        }

        scanner.close();
    }

    // ------------------------------------------------------------------ //
    //  MENU
    // ------------------------------------------------------------------ //
    private static void printMenu() {
        System.out.println("----------------------------------------------");
        System.out.println(" MENU");
        System.out.println("  1. Add a new product & produce items");
        System.out.println("  2. Display production log");
        System.out.println("  3. Display production statistics");
        System.out.println("  4. Exit");
        System.out.println("----------------------------------------------");
        System.out.print("Enter choice: ");
    }

    // ------------------------------------------------------------------ //
    //  OPTION 1 – Add product and produce items
    // ------------------------------------------------------------------ //
    private static void addProduct() {
        System.out.println("\n--- Add New Product ---");

        System.out.print("Enter product name : ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("[!] Product name cannot be empty.\n");
            return;
        }

        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("How many items to produce? ");
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("[!] Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid number. Please try again.");
            }
        }

        // Produce the requested number of items using an anonymous subclass of Product
        for (int i = 0; i < quantity; i++) {
            Product item = new Product(name) {
            };
            productionLog.add(item);
        }

        // Update catalog count
        productCatalog.merge(name, quantity, Integer::sum);

        System.out.printf("%n[✓] Produced %d item(s) of '%s'.%n%n", quantity, name);
    }

    // ------------------------------------------------------------------ //
    //  OPTION 2 – Display full production log
    // ------------------------------------------------------------------ //
    private static void displayProductionLog() {
        System.out.println("\n--- Production Log ---");

        if (productionLog.isEmpty()) {
            System.out.println("No items have been produced yet.\n");
            return;
        }

        for (Product p : productionLog) {
            System.out.println("- - - - - - - - - - - - - - -");
            System.out.println(p);
        }
        System.out.println("- - - - - - - - - - - - - - -\n");
    }

    // ------------------------------------------------------------------ //
    //  OPTION 3 – Statistics
    // ------------------------------------------------------------------ //
    private static void displayStatistics() {
        System.out.println("\n--- Production Statistics ---");

        if (productionLog.isEmpty()) {
            System.out.println("No production data available yet.\n");
            return;
        }

        int totalItems = productionLog.size();
        int uniqueProducts = productCatalog.size();

        System.out.println("Total items produced  : " + totalItems);
        System.out.println("Unique products       : " + uniqueProducts);
        System.out.println();
        System.out.println("Breakdown by product:");

        List<String> sortedNames = new ArrayList<>(productCatalog.keySet());
        Collections.sort(sortedNames);

        for (String productName : sortedNames) {
            int count = productCatalog.get(productName);
            double pct = (count * 100.0) / totalItems;
            System.out.printf("  %-25s : %3d item(s)  (%.1f%%)%n", productName, count, pct);
        }

        System.out.println();

        String topProduct = productCatalog.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        System.out.println("Most produced product : " + topProduct);
        System.out.println();
    }
}
