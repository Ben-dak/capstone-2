package models;

public class Drink implements OrderInterface {
    private final String flavor;
    private int size;
   // getPrice()uses the value of size to determine and return the correct price

    public Drink(String flavor, int size) {
        this.flavor = flavor;
        this.size = size; // "this.size" refers to the object's field then sets it to the size passed in to the parenthesis above
    }

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
        return String.format("%s (%d oz)", flavor, size);
    }

    public int getSize() {
        return size;  // Returns the current size value
    }
}
