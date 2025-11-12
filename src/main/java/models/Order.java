package models;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    //Fields
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;
    private double totalPrice;
    private LocalDateTime orderDateTime;

    //Constructors
    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
    }
}
/*
// THIS CLASS IS LIKE A SHOPPING CART - it doesn’t make sandwiches or pour drinks,
    but it knows what’s inside and how much it all costs.
    It MANAGES the collection of items.

//Hold Order Items (sandwiches, drinks, chips) - Workbook 6 - Portfolio
//Maintain ArrayList<Sandwich>, ArrayList<Drink>, and ArrayList<Chips> collections

//The Order class manages everything related to a single customer's transaction

 */
