package models;

public class Chips implements OrderInterface {
    private String flavor;

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String getName() {
        return flavor;
    }

    public Chips(String flavor) {
        this.flavor = flavor;
    }
}
