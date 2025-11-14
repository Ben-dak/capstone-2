package models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderInterface {
    // Constants
    public static final String breadWhite = "White bread";
    public static final String breadWheat = "Wheat bread";
    public static final String breadRye = "Rye bread";
    public static final String breadWrap = "Wrap";

    private final String bread;   // constant that is picked in UserInterface
    private final int size;
    private final boolean toasted;
    private final List<Toppings> toppings = new ArrayList<>(); //collection of toppings

    public Sandwich(String bread, int size, boolean toasted) { // will be utilized when creating a ssandwich
        this.bread = breadChoice(bread); // uses the breadChoicePicked method to store the choice in the "bread" field
        this.size = size;
        this.toasted = toasted;
    }

    public void addTopping(Toppings topping) { //// NOTE TO ADD THIS TO UI
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        // getting price based on what is selected
        double price = basePrice(size); // base price of sandwich

        for (Toppings toppingSelected : toppings) { //adds cost for each topping
            String type = toppingSelected.getType();
            if (Toppings.typeMeat.equals(type)) {
                price += meatPrice(size); // takes value of price and adds the value from meatPrice(size) and stores new total back into price
                if (toppingSelected.isExtra()) price += extraMeatPrice(size); // Add extra charge if its marked "extra"
            } else if (Toppings.typeCheese.equals(type)) { // If it’s cheese, do the same for cheese
                price += cheesePrice(size);
                if (toppingSelected.isExtra()) price += extraCheesePrice(size);// Add extra charge if its marked "extra"
            }
        }
        return price;
    }

    @Override
    public String getName() {
        String toastTag = ""; // no label
        if (toasted) {
            toastTag = " - Toasted"; // if its toasted it will display this
        }

        return String.format("%d\" %s Sandwich%s", size, bread, toastTag); // returns the formatted tecxt as a string
    }

    public List<Toppings> getToppings() {
        return new ArrayList<>(toppings);
        // creates a new arraylist of toppings when getToppings() is called
    }

    ///  WANT TO USE THIS, but I am afraid I cant explain the "return switch" as good as
    /// - I could explain the classic switch statement - will use the below instead
//    public static double basePrice(int size) {
//        return switch (size) {
//            // return switch (size) - the switch evaluates to a single value
//            // based on the selected size (what was selected in the console)
//            case 4 -> 5.50;
//            case 8 -> 7.00;
//            case 12 -> 8.50;
//            default -> 0.0;
//        };
//    }

    public static double basePrice(int size) {
        double result = 0.0;
        // This is Defensive coding as it ensures that result ALWAYS has a safe starting value
        switch (size) {
            case 4:  result = 5.50;
                break;
            case 8:  result = 7.00;
                break;
            case 12: result = 8.50;
                break;
            default: result = 0.0;
                break;
        }
        return result;
    }

    public static double meatPrice(int size) {
        double result = 0.0;
        switch (size) {
            case 4:
                result = 1.00;
                break;
            case 8:
                result = 2.00;
                break;
            case 12:
                result = 3.00;
                break;
            default:
                result = 0.0;
                break;
        }
        return result;
    }

    public static double extraMeatPrice(int size) {
        double result = 0.0;
        switch (size) {
            case 4:
                result = 0.50;
                break;
            case 8:
                result = 1.00;
                break;
            case 12:
                result = 1.50;
                break;
            default:
                result = 0.0;
                break;
        }
        return result;
    }

    public static double cheesePrice(int size) {
        double result = 0.0;
        switch (size) {
            case 4:
                result = 0.75;
                break;
            case 8:
                result = 1.50;
                break;
            case 12:
                result = 2.25;
                break;
            default:
                result = 0.0;
                break;
        }
        return result;
    }

    public static double extraCheesePrice(int size) {
        double result = 0.0;
        switch (size) {
            case 4:
                result = 0.30;
                break;
            case 8:
                result = 0.60;
                break;
            case 12:
                result = 0.90;
                break;
            default:
                result = 0.0;
                break;
        }
        return result;
    }

    // This method for bread choices, default is white if not asked
    private static String breadChoice(String input) {
        if (input == null) return breadWhite; // if no bread choice (input), return "White" as the default bread
        String s = input.trim();

        if (s.equalsIgnoreCase(breadWhite))
            return breadWhite;
        if (s.equalsIgnoreCase(breadWheat))
            return breadWheat;
        if (s.equalsIgnoreCase(breadRye))
            return breadRye;
        if (s.equalsIgnoreCase(breadWrap))
            return breadWrap;

        return breadWhite;
    }
}

/// += means - Add and assign
/// -> arrow operator - The -> arrow separates the case condition (I use this so I dont have to use a break/its cleaner)
/// return switch (size) - The switch evaluates to a single value based on the selected size (what was selected in the console)
/// return List.copyOf(toppings) - Returns a copy of the toppings list that the user selected while building their sandwich