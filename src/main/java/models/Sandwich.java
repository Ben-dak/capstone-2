package models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements OrderInterface {
    // Constants
    public static final String breadWhite = "White";
    public static final String breadWheat = "Wheat";
    public static final String breadRye = "Rye";
    public static final String breadWrap = "Wrap";

    private final String bread;   // constant that is picked in UserInterface
    private final int size;
    private final boolean toasted;
    private final List<Toppings> toppings = new ArrayList<>(); //collection of toppings

    public Sandwich(String bread, int size, boolean toasted) { // will be utilized when creating a ssandwich
        this.bread = breadChoicePicked(bread); // uses the breadChoicePicked method to store the choice in the "bread" field
        this.size = size;
        this.toasted = toasted;
    }

    public void addTopping(Toppings topping) { //// NOTE TO ADD THIS TO UI
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        double price = basePrice(size); // base price of sandwich

        for (Toppings t : toppings) { //adds cost for each topping
            String type = t.getType();
            if (Toppings.typeMeat.equals(type)) {
                price += meatPrice(size); // takes value of price and adds the value from meatPrice(size) and stores new total back into price
                if (t.isExtra()) price += extraMeatPrice(size); // Add extra charge if its marked "extra"
            } else if (Toppings.typeCheese.equals(type)) { // If it’s cheese, do the same for cheese
                price += cheesePrice(size);
                if (t.isExtra()) price += extraCheesePrice(size);
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
        return List.copyOf(toppings); //returns a copy of the toppings list that the user selected while building their sandwich
    }                                 //so when getToppings() is called for my show cart method it shows what was chose and cant be modified
    public int getSize() {
        return size;
    }
    public String getBread() {
        return bread;
    }
    public boolean isToasted() {
        return toasted;
    }

    public static double basePrice(int size) {
        return switch (size) {
            // return switch (size) - the switch evaluates to a single value
            // based on the selected size (what was selected in the console)
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 0.0;
        };
    }

    public static double meatPrice(int size) {
        return switch (size) {
            case 4 -> 1.00;
            case 8 -> 2.00;
            case 12 -> 3.00;
            default -> 0;
        };
    }

    public static double extraMeatPrice(int size) {
        return switch (size) {
            case 4 -> .50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 0;
        };
    }

    public static double cheesePrice(int size) {
        return switch (size) {
            case 4 -> .75;
            case 8 -> 1.50;
            case 12 -> 2.25;
            default -> 0;
        };
    }

    public static double extraCheesePrice(int size) {
        return switch (size) {
            case 4 -> 0.30;  // small pizza = 30 cents extra
            case 8 -> 0.60;  // medium pizza = 60 cents extra
            case 12 -> 0.90; // large pizza = 90 cents extra
            default -> 0.0;  // anything else = no extra cheese charge
        };
    }

    // This method for bread choices, default is white if not asked
    private static String breadChoicePicked(String input) {
        if (input == null) return breadWhite;
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