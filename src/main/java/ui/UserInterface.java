package ui;

import models.Chips;
import models.Drink;
import models.OrderInterface;
import models.Sandwich;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    // final means variable cant be reassigned once created
    private final Scanner myScanner = new Scanner(System.in);

    //create a list to hold all the user's selected items (I made this like a shopping cart)
    // Its typed to OrderInterface so we can store any object that implements that interface
    private final List<OrderInterface> cart = new ArrayList<>();

    // The MAIN loop that displays the menu and processes user commands
    public void display() {
        boolean running = true;  // Controls the main loop (what I used before, like it's running
        while (running) {        // Runs continuously until running is set to false
            printMenu();// calls the printMenu helper method
            System.out.print("Choose an option: ");
            String input = myScanner.nextLine().trim();    // Get user input and remove spaces

            //Handles which action to take based on the user's input
            switch (input) {
//                case "1" -> addSandwich();
                case "2" -> addDrink();//If user enters 1 call addDrink()
                case "3" -> addChips();
                case "4" -> showCart();
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("Thank you, come again!"); // Message when the user exits the program
    }

    // print menu to be used so things look cleaner
    private void printMenu() {
        System.out.println("=== Bendak's Sandwiches Menu ===");
        System.out.println("2) Add Drink"); //will make sandwiches #1 later but using this to test
        System.out.println("3) Add Chips");
        System.out.println("4) Show Cart");
        System.out.println("0) Exit");
    }

    public void addChips() {
        System.out.println("=== Add Chips ==="); // I guess crisps, these arent fries Dave LOL

        // Display the list of available flavors
        System.out.println("Choose a flavor:");
        System.out.println("1) Plain Chips");
        System.out.println("2) BBQ Chips");
        System.out.println("3) Spicy Chips");
        System.out.println("4) Cheddar Chips");

        // Ask for a flavor choice
        int choice = 0;
        boolean validFlavor = false;

        while (!validFlavor) {
            System.out.print("Flavor (1-4): ");

            if (myScanner.hasNextInt()) {
                choice = myScanner.nextInt();
                myScanner.nextLine();

                if (choice >= 1 && choice <= 4) {
                    validFlavor = true;
                } else {
                    System.out.println("Choose 1 through 4.");
                }
            } else {
                System.out.println("Enter a number.");
            }
        }
        // Determine the flavor based on users choice
        String flavor = switch (choice) {
            case 1 -> "Plain";
            case 2 -> "BBQ";
            case 3 -> "Spicy";
            case 4 -> "Cheddar";
            default -> "Unknown";
        };

        // Create a new Drink object with the selected flavor and size
        Chips chipOption = new Chips(flavor);

        // Add the new Drink to the cart list
        cart.add(chipOption);

        // Print a confirmation showing what was added, its size, and its price
        // %s = string, %d = integer, %.2f = decimal with 2 digits, %n = newline
        System.out.printf("Added: %s - $%.2f%n", chipOption.getName(), chipOption.getPrice());
    }

    private String breadChoicePicked() {
        // Show the bread menu to the user
        System.out.println("Choose bread:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");

        String input;
        input = myScanner.nextLine();
        input = input.trim();

        return switch (input) {
            case "1" -> Sandwich.breadWhite;
            case "2" -> Sandwich.breadWheat;
            case "3" -> Sandwich.breadRye;
            case "4" -> Sandwich.breadWrap;
            default -> Sandwich.breadWhite; // default if user doesnt enter
        };
    }

    private void addSandwich(){
//        System.out.println("=== Add Sandwich===");
//        String bread = breadChoicePicked();
//
//        int size;
//        while (true) {
//            System.out.println();
//        }
    }

    // Adds a new drink to the cart.
    private void addDrink() {
        System.out.println("=== Add Drink ===");  // header

        // Ask user for the size in oz
        int size = 0;
        boolean validSize = false;

        // Validate that the size is one of the allowed options.
        Scanner myScanner = new Scanner(System.in);

        while (!validSize) {
            System.out.println("Sizes (4, 8, or 12): ");
            System.out.print("Choose a size: ");
            size = myScanner.nextInt();

            if (size == 4) {
                validSize = true;
            } else {
                if (size == 8) {
                    validSize = true;
                } else {
                    if (size == 12) {
                        validSize = true;
                    } else {
                        System.out.println("Invalid size. Must select 4, 8, or 12.");
                    }
                }
            }
        }

        // Display the list of available flavors
        System.out.println("Choose a flavor:");
        System.out.println("1) Cola");
        System.out.println("2) Lemonade");
        System.out.println("3) Coffee");
        System.out.println("4) Goober Juice");

        // Ask for a flavor choice
        int choice = 0;
        boolean validFlavor = false;
        while (!validFlavor) {
            System.out.print("Flavor (1-4): ");
            if (myScanner.hasNextInt()) {
                choice = myScanner.nextInt();
                myScanner.nextLine();

                if (choice >= 1 && choice <= 4) {
                    validFlavor = true;
                } else {
                    System.out.println("Please choose 1 through 4.");
                }
            } else {
                System.out.println("Please enter a number.");
            }
        }
        // Determine the flavor based on user' number choice
        String flavor = switch (choice) {
            case 1 -> "Cola";
            case 2 -> "Lemonade";
            case 3 -> "Coffee";
            case 4 -> "Goober Juice";
            default -> "Unknown";
        };

        // Create a new Drink object with the selected flavor and size
        Drink drink = new Drink(flavor, size);

        // Add the new Drink to the cart list
        cart.add(drink);

        // Print a confirmation showing what was added, its size, and its price
        // %s = string, %d = integer, %.2f = decimal with 2 digits, %n = newline
        System.out.printf("Added: %s (%d oz) - $%.2f%n", drink.getName(), drink.getSize(), drink.getPrice());
    }

    // Displays all items currently in the cart.
    private void showCart() {
        System.out.println("=== Cart ===");
        if (cart.isEmpty()) {  // Check if there are no items in the cart (I added ! at the beginning initially lol)
            System.out.println("Empty cart.");
            return;  // Exit method early if nothing to show
        }

        int i = 1;  // numbers each item in the cart.

        // Loop through every item in the cart list
        // Default name (works for any object implementing OrderInterface)
        for (OrderInterface item : cart) { // For each item of type OrderInterface in cart
            String displayName = item.getName();

            // If the current item is a Drink, we want to also show its size
            if (item instanceof Drink drink) { // instanceof asks if is this object actually a Drink
                // Use String.format() to combine flavor and size neatly
                displayName = String.format("%s (%d oz)", drink.getName(), drink.getSize());
            }

            // Print the numbered list of items with their names and prices aligned
            System.out.printf("%d) %-24s $%.2f%n", i++, displayName, item.getPrice());
        }

        // After showing all items, print the total cost of the cart
        System.out.printf("Total: $%.2f%n", getCartTotal());
    }

    // Calculates the total cost of all items in the cart.
    private double getCartTotal() {
        double total = 0.0;  // Start with 0.
        // Loop through every item and add up the prices.
        for (OrderInterface item : cart) total += item.getPrice();
        return total;  // Return the final total.
    }
}