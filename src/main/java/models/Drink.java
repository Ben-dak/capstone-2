package models;

public class Drink implements OrderInterface {
    //Name, size - Workbook 5 - BankAccount
    private String flavor;
    private int size;

    /*
    In this specific Drink implementation, getPrice()uses the value of size
     to determine and return the correct price.
     It tells us what size will be what price when the user is prompted to select 4, 8, or 12
     */
    @Override
    public double getPrice() {
        if (size == 4) {
            return 2.00;
        }if (size == 8) {
            return 2.50;
        }if (size == 12) {
            return 3.00;
        } else {
            return 0;
        }
    }

    @Override
    public String getName() {
        return flavor;
    }

    public Drink(String flavor, int size) {
        this.flavor = flavor;
        this.size = size; // "this.size" refers to the object's field then sets it to the size passed in to the parenthesis above
    }

    public int getSize() {
        return size;  // Returns the current size value
    }
    public void setSize(int size) {
        this.size = size; // Update the object's size
    }

}
