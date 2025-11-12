package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderItems {

    //Fields
    private List<Sandwich> sandwiches; // List is an Interface
    private List<Drink> drinks;
    private List<Chips> chips;
    private double totalPrice;
    private LocalDateTime orderDateTime;

    //Constructors
    public OrderItems() {
        this.sandwiches = new ArrayList<>(); // using this to make an ArrayList to provide the behavior for sandwiches List
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
    }
}

/// private List<Sandwich> sandwiches; - is an Interface in Java that declares a variable of type List

/// THIS CLASS IS LIKE A SHOPPING CART - It MANAGES the collections of order items

///Hold Order Items in Lists (sandwiches, drinks, chips) -
///Maintain ArrayList<Sandwich>, ArrayList<Drink>, and ArrayList<Chips> collections
///The Order class manages everything related to a single customer's transaction

