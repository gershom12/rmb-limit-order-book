package main.java.com.gershom.model;

// main.Order class representing an order
public class Order {
    private static int nextId = 1; // Static variable to generate unique order IDs
    private int id;
    private double price;
    private int quantity;
    private String side; // Buy or Sell

    public Order(double price, int quantity, String side) {
        this.id = nextId++;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSide() {
        return side;
    }

    // Method to modify order quantity
    public void modifyQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}
