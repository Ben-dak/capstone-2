package models;

public interface OrderInterface {

    double getPrice();
    String getName();
}

// This interface defines a contract for things that can be ordered (Sandwich, Drink, and Chips)

//    int size(); - Try adding this instead of in each class
