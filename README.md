# <center>Bendak's Sandwich Shop

---

This program allows the user to build an order from the sandwich shop and checkout. 
When an order is confirmed, a receipt is generated and written to a text file.

---

## Project Overview

- Console-based sandwich ordering system.
- Fully build customized sandwiches down to the toppings, sides, and sauces.
- Add drinks and chips to a shared cart.
- View cart, see the total, and confirm or cancel orders.
- On checkout, a receipt is generated and saved to a text file. 

---

## UML Diagram

![sandwichShopUML.png](src/main/resources/images/sandwichShopUML.png)
---

## My Classes and Their Uses

### Interfaces

| Interface                | Purpose                                                                                                          |
|--------------------------|------------------------------------------------------------------------------------------------------------------|
| <center> `OrderInterface` | <center> Ensures all orderable items provide `getName()` and `getPrice()`, so they can be handled in one cart. |

---

### Models

| Class          | Purpose                                                                                                                                                      |
|----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <center> `Sandwich` | <center> Represents a customizable sandwich (bread, size, toasted) and holds a `List<Toppings>`. Calculates total sandwich price based on size and toppings. |
| <center> `Drink`    | <center> Represents a drink with `flavor` and `size`. Price is determined by drink size.                                                                     |
| <center> `Chips`    | <center> Represents a bag of chips with a chosen `flavor` and a set price.                                                                                   |
 Stores all topping options in static arrays.     |
---

### UI & Utility

| Class             | Purpose                                                                                                                                 |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| <center> `UserInterface` | <center> Handles all console screens and user input. Manages a `List<OrderInterface>` cart, builds items, shows cart, and runs checkout. |
| <center> `Program`       | <center> Entry point (`main` method). Starts the app by creating `UserInterface` and calling `display()`.                        |
| <center> `ReceiptWriter` | <center> Writes the final receipt text to a timestamped `.txt` file in `src/main/resources/receipts`.                           |

---

## <center>Usage Display

#### Screens

(PUT SCREENSHOTS OF SCREENS HERE)

---

## <center>Interesting Feature

(PUT THE SHOW CART HERE)