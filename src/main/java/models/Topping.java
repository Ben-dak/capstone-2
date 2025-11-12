package models;

public class Topping {
    // Type constants (use these exact strings everywhere)
    public static final String typeMeat = "Meat";
    public static final String typeCheese = "Cheese";
    public static final String typeVeggies = "Veggies";
    public static final String typeSauce = "Sauce";
    public static final String typeSide = "Side";

    // Arrays of strings - toppings (static, they belong to the class)
    public static final String[] meats = {
            "steak", "ham", "salami", "roast beef", "chicken", "bacon"
    };
    public static final String[] cheeses = {
            "american", "provolone", "cheddar", "swiss"
    };
    public static final String[] veggies = {
            "lettuce", "peppers", "onions", "tomatoes", "jalapeños",
            "cucumbers", "pickles", "guacamole", "mushrooms"
    };
    public static final String[] sauces = {
            "mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"
    };
    public static final String[] sides = {
            "au jus", "sauce"
    };

    private final String name;
    private final String type;
    private final boolean extra;

    public Topping(String name, String type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public boolean isExtra() {
        return extra;
    }
}
