package ui;

import models.*;
import util.ReceiptWriter;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner myScanner = new Scanner(System.in);
    // final means variable cant be reassigned once created

    private final List<OrderInterface> cart = new ArrayList<>();
    // create a list to hold all the user's selected items (I made this like a shopping cart)
    // Its typed to OrderInterface so we can store any object that implements that interface

    // The loop that displays the home screen then calls orderLoop method
    public void display() {
        boolean running = true;

        while (running) {
            System.out.println("=== Bendak's Sandwich Shop ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            String input = myScanner.nextLine().trim();

            switch (input) {
                case "1":
                    // start a brand-new order
                    cart.clear();
                    // just in case something is in cart
                    orderLoop();
                    // go to the Order Screen
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option, try again.");
                    break;
            }
        }
        System.out.println("Thank you, come again!");
    }

    // menu to be used by display method so things look cleaner
    private void orderLoop() {
        boolean running = true;

        while (running) {
            System.out.println("=== New Order ===");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Show Cart");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");

            String input = myScanner.nextLine().trim();

            switch (input) {
                case "1" -> addSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> showCart();
                case "5" -> checkout();
                case "0" -> {
                    // Cancel the order and go back to Home Screen
                    cart.clear();

                    System.out.println("Order canceled. Returning to home screen.");
                    running = false;   // breaks out of this loop, back to display()
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void addChips() {
        System.out.println("=== Add Chips ===");

        System.out.println("Choose a flavor:");
        System.out.println("1) Plain Chips");
        System.out.println("2) BBQ Chips");
        System.out.println("3) Spicy Chips");
        System.out.println("4) Cheddar Chips");

        // hold the user's choice and track whether it’s valid
        int choice = 0;
        boolean validFlavor = false;

        // loops until valid flavor is picked
        while (!validFlavor) {
            System.out.print("Flavor: ");

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
            case 1 -> "Plain Chips";
            case 2 -> "BBQ Chips";
            case 3 -> "Spicy Chips";
            case 4 -> "Cheddar Chips";
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

    private String breadChoice() {
        // Show the bread menu to the user
        System.out.println("Choose bread:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
        System.out.print("Choose an option: ");

        String input;
        input = myScanner.nextLine();
        input = input.trim();

        return switch (input) {
            case "1" -> Sandwich.breadWhite;
            case "2" -> Sandwich.breadWheat;
            case "3" -> Sandwich.breadRye;
            case "4" -> Sandwich.breadWrap;
            default -> Sandwich.breadWhite; // default if user doesn't enter
        };
    }

    private void addDrink() {
        System.out.println("=== Add Drink ===");  // header

        int size = 0;
        boolean validSize = false;
        // Validate that the size is one of the allowed options

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
        // Determine the flavor based on user's number choice
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

    private void showCart() {
        System.out.println("Cart:"); //header
        if (cart.isEmpty()) { //if List<OrderInterface> is empty, state cart is empty
            System.out.println("Empty cart.");
            return;  // Exit method early if nothing to show
        }
        int i = 1;  // numbers each item in the cart

        // Loop through every item in the cart list
        for (OrderInterface item : cart) { // For each item, of type OrderInterface, in cart
            String displayName = item.getName(); // calls getName on the item

            System.out.printf("%d) %-24s $%.2f%n", i++, displayName, item.getPrice());
            // Print the numbered list of items with their names and prices assigned
        }
        // After showing all items, print the total cost of the cart
        System.out.printf("Total: $%.2f%n", getCartTotal()); //calls getCartTotal to actually get the total
    }

    private double getCartTotal() {
        double total = 0.0;  // Start with 0
        // Loop through every item and add up the prices
        for (OrderInterface item : cart) total += item.getPrice();
        return total;  // Return the final total
    }

    private void checkout() {
        // Handles the checkout process for all items currently in the cart

        if (cart.isEmpty()) {
            // if cart empty
            System.out.println("Cart is empty.");
            return;
            // return - exits the method
        }

        System.out.println("=== Checkout ===");
        // header

        showCart();
        // calls helper method showCart

        System.out.print("Confirm order? (y/n): ");

        String confirmOrder = myScanner.nextLine().trim().toLowerCase();
        // Reads the users input, trims extra spaces, and converts it to lowercase

        if (!confirmOrder.startsWith("y")) {
            // if anything other than a word starting with y or Y is typed,
            // cancel checkout (that way "n" or "no" will cancel too)
            System.out.println("Checkout canceled.");
            return;
        }

        StringBuilder sBuilder = new StringBuilder();
        // create a long string in the receipt

        LocalDateTime dtNow = LocalDateTime.now();
        // Captures the current date and time when the order is being placed

        int line = 1;
        // variable for numbering the receipt

        for (OrderInterface item : cart) {
            // Loops through each item in the cart
            String displayName = item.getName();

            if (item instanceof Drink drink) {
                // If the item is a Drink, include its size
                displayName = String.format("%s (%d oz)", drink.getName(), drink.getSize());
            }

            sBuilder.append(String.format("%d) %-24s $%.2f%n", line++, displayName, item.getPrice()));
            // adds the line number (from console input), product name, and price to the string builder

            if (item instanceof Sandwich) {
                // If the item is a Sandwich, it may have multiple toppings to list
                Sandwich sandwich = (Sandwich) item;
                // cast item to Sandwich

                for (Toppings toppings : sandwich.getToppings()) {
                    // Loops through all toppings for this sandwich
                    sBuilder.append("    - ").append(toppings.getType()).append(": ").append(toppings.getName());
                    // adds(appends) the dash, the topping type(Sauce), then topping name to the builder
                    if (toppings.isExtra()) sBuilder.append(" - extra");
                    // calls isExtra() if the topping is extra then adds to builder
                    sBuilder.append(System.lineSeparator()); /// I remembered that System is a built-in utility class (just a small win note)
                    // lineSeparator adds a line break after each entry (Separates toppings)
                }
            }
        }

        sBuilder.append(String.format("Total: $%.2f%n", getCartTotal()));
        // Adds a final line showing the total price of all cart items

        //writer
        ReceiptWriter bWriter = new ReceiptWriter();
        // creates an instance of receipt writer class to handle file writing

        Path path = bWriter.writeReceipt(sBuilder.toString(), dtNow);
        // Writes the complete receipt text to the file using the current date and time
        // Returns a Path object that points to where the file was saved

        System.out.println("Receipt saved to: " + path);
        // Prints file path (I can leave this out if it messes up)

        cart.clear();
        // clears the shopping cart sso I can start over

        System.out.println("Your order has been completed");
    }

    private void addSandwich() {
        System.out.println("=== Add Sandwich ===");
        String bread = breadChoice(); // calls helper method

        int size; // sandwich size
        while (true) {
            System.out.print("Choose size (4, 8, 12): "); // prompt user for sandwich size
            try {
                size = Integer.parseInt(myScanner.nextLine().trim());
                /// Reads the user’s input as text, removes extra spaces,
                /// converts that text into an integer, and stores the result in the variable size

                if (size == 4) {
                    break;
                } else if (size == 8) {
                    break;
                } else if (size == 12) {
                    break;
                }
            } catch (NumberFormatException ignored) { // catch error and continue loop (don't need the exception object (e))
            }
            System.out.println("Invalid size. Must be 4, 8, or 12.");
        }

        // calls toastedOptions and stores the result in toasted variable
        boolean toasted = toastedOptions("Toasted? (y/n): ");

        // create a Sandwich object using the chosen bread, size, and toasted option
        Sandwich sandwich = new Sandwich(bread, size, toasted);

        /// MEATS
        // print a numbered list of all available meats, with a header message
        printOptionsNumbered("Choose your meat:", Toppings.meats);

        int meatChoice; // stores the number of the meat selected
        while (true) { // keep asking until valid option chosen
            System.out.print("Select a meat by number: ");
            try {
                meatChoice = Integer.parseInt(myScanner.nextLine().trim()); // read input, trim spaces, convert to int
                if (meatChoice >= 1 && meatChoice <= 6) {
                    break; // valid choice (within range of the meats array), exit the loop
                }
            } catch (NumberFormatException ignored) { // catch error and continue loop
            }
            System.out.println("Invalid selection.");
        }

        //meat array
        String meatName = Toppings.meats[meatChoice - 1];
        // Convert the chosen number into the actual meat name
        // (arrays are 0-based, but user choices start at 1, so we use choice - 1)

        boolean meatExtra = toastedOptions("Extra " + meatName + "? (y/n): ");
        // Ask if they want extra of the selected meat (returns true if answer starts with "y")

        sandwich.addTopping(new Toppings(meatName, Toppings.typeMeat, meatExtra));
        // Create a new Toppings object for the meat and add it to the sandwich's toppings list  (extra if it's added)

        /// CHEESE
        printOptionsNumbered("Choose your cheese:", Toppings.cheeses);

        int cheeseChoice;
        while (true) {
            System.out.print("Select a cheese by number (or 0 for no cheese): ");
            try {
                cheeseChoice = Integer.parseInt(myScanner.nextLine().trim()); // read input, trim spaces, convert to int

                if (cheeseChoice == 0) { // no cheese
                    break;
                }
                if (cheeseChoice >= 1 && cheeseChoice <= 4) {
                    break;
                }
            } catch (NumberFormatException e) {
                // if the input isn't a valid number - error message
            }
            System.out.println("Invalid selection. Please choose a number from the list.");
        }

        if (cheeseChoice != 0) { // if they pick no cheese do not prompt user for extra
            String cheeseName = Toppings.cheeses[cheeseChoice - 1]; // convert choice number to actual cheese name
            boolean cheeseExtra = toastedOptions("Extra " + cheeseName + "? (y/n): ");
            // ask if they want extra cheese on the sandwich

            sandwich.addTopping(new Toppings(cheeseName, Toppings.typeCheese, cheeseExtra));
            // add the cheese topping to the sandwich
        }

        /// VEGGIES
        printOptionsNumbered("Choose veggies:", Toppings.veggies);
        // print a numbered list of all veggies with a header message

        System.out.print("Select veggies by number (Separate with comma): ");
        String line = myScanner.nextLine().trim();
        // Reads the user’s sauce selections and trims whitespaces

        if (!line.isEmpty()) {
            // If the user typed anything, process the choices
            String[] parts = line.split(",");
            // splits the input into separate entries (like this: "1,3,4")

            for (String NumberChoices : parts) {
                NumberChoices = NumberChoices.trim();
                // Remove extra spaces around each number

                try {
                    int vegChoice = Integer.parseInt(NumberChoices);
                    // Convert the string pNumberChoices - to an integer

                    if (vegChoice >= 1 && vegChoice <= 9) {
                        // number range for veggies

                        String veggieName = Toppings.veggies[vegChoice - 1];
                        // Shows the user’s choice as the corresponding veggie string

                        sandwich.addTopping(new Toppings(veggieName, Toppings.typeVeggies, false));
                        // Adds the selected veggie as a topping to the sandwich (no extras)
                    } else {
                        System.out.println("Ignoring invalid veggie number: " + vegChoice);
                        // Any number outside the valid range is ignored
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ignoring invalid entry: " + NumberChoices);
                    // If the user typed something that isn't a number then this skip it
                }
            }
        }

        /// SAUCES
        printOptionsNumbered("Choose sauces:", Toppings.sauces);
        // print a numbered list of all sauces with a header message

        System.out.print("Select sauces by number (Separate with comma): ");
        String sauceLine = myScanner.nextLine().trim();
        // Reads the users sauce selections and trims whitespace

        if (!sauceLine.isEmpty()) {
            // If the user typed anything, process the choices
            String[] parts = sauceLine.split(",");
            // splits the input into separate entries (like this: 1,3,4)

            for (String NumberChoices : parts) {
                NumberChoices = NumberChoices.trim();
                // Remove extra spaces around each number

                try {
                    int sauceChoice = Integer.parseInt(NumberChoices);
                    // Convert the entry into an integer

                    if (sauceChoice >= 1 && sauceChoice <= Toppings.sauces.length) {
                        // Ensure the choice is within the valid sauce range

                        String sauceName = Toppings.sauces[sauceChoice - 1];
                        // Look up the sauce name based on the number provided

                        sandwich.addTopping(new Toppings(sauceName, Toppings.typeSauce, false));
                        // Add this sauce topping to the sandwich
                    } else {
                        System.out.println("Ignoring invalid sauce number: " + sauceChoice);
                        // If out of range, notify user and skip it
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ignoring invalid entry: " + NumberChoices);
                    // If not a number - skip that entry
                }
            }
        }


        /// Sides
        printOptionsNumbered("Choose sides:", Toppings.sides);
        // print a numbered list of all sauces with a header message

        System.out.print("Select sides by number (Separate with comma): ");
        String sideLine = myScanner.nextLine().trim();
        // Reads the line of side selections from the user

        if (!sideLine.isEmpty()) {
            // Only continue if the user entered something
            String[] parts = sideLine.split(",");
            // splits the input into separate entries (like this: 1,3,4)

            for (String NumberChoices : parts) {
                NumberChoices = NumberChoices.trim();
                // Remove extra spaces around each number

                try {
                    int sideChoice = Integer.parseInt(NumberChoices);
                    // Parse the entry into an integer

                    if (sideChoice >= 1 && sideChoice <= 2) {
                        // Ensure the choice is within the valid sauce range

                        String sideName = Toppings.sides[sideChoice - 1];
                        // Look up the sauce name based on the number provided

                        sandwich.addTopping(new Toppings(sideName, Toppings.typeSide, false));
                        // Add the side to the cart
                    } else {
                        System.out.println("Ignoring invalid side number: " + sideChoice);
                        // If out of range, notify user and skip it
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ignoring invalid entry: " + NumberChoices);
                    // If not a number - skip that entry
                }
            }
        }

        // add created sandwich to the cart
        cart.add(sandwich);
        System.out.printf("Added: %s - $%.2f%n", sandwich.getName(), sandwich.getPrice());
        // confirm to the user what was added and show the price (the format is to 2 decimal places)
    }

    private boolean toastedOptions(String prompt) {
        System.out.print(prompt); // displays prompt that is passed in above ("Toasted? (y/n): ")
        return myScanner.nextLine().trim().toLowerCase().startsWith("y");
        // reads a line of input, trims spaces, converts to lowercase,
        // and returns true if the response starts with "y" (accepts anything else as "no")
    }

    private void printOptionsNumbered(String header, String[] items) {
        // String[] items is for every item in the array (meats, cheese, etc. -
        // shown above like this: Toppings.meats[meatChoice - 1])

        System.out.println(header);
        for (int i = 0; i < items.length; i++) {
            // loops through array
            // i starts at 0 (arrays are 0 based)
            // the loop continues until i reaches the last index
            System.out.println((i + 1) + ") " + items[i]);
            // prints each item with a number (starting at 1 bc of "(i+1)")
        }
    }

}

