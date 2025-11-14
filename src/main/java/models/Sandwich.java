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

    public Sandwich(String bread, int size, boolean toasted) { // will be utilized when creating a sandwich
        this.bread = breadChoice(bread); // uses the breadChoicePicked method to store the choice in the "bread" field
        this.size = size;
        this.toasted = toasted;
    }

    public void addTopping(Toppings topping) {
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

        return String.format("%d\" %s Sandwich%s", size, bread, toastTag); // returns the formatted text as a string
    }

    public List<Toppings> getToppings() {
        return new ArrayList<>(toppings);
        // creates a new arraylist of toppings when getToppings() is called
    }

    public static double basePrice(int size) {
        double result = 0.0;
        // This is Defensive coding as it ensures that result ALWAYS has a safe starting value (0.0)
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