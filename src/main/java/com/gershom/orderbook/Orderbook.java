package main.java.com.gershom.orderbook;

import main.java.com.gershom.model.Order;

import java.util.*;

// main.gershom.orderbook.Orderbook class managing the limit order book
public class Orderbook {
    // Map to store orders by price and side
    private Map<Double, Map<String, Queue<Order>>> orders;

    public Orderbook() {
        orders = new HashMap<>();
    }

    // Method to add an order to the orderbook
    public void addOrder(Order order) {
        orders.putIfAbsent(order.getPrice(), new HashMap<>());
        orders.get(order.getPrice()).putIfAbsent(order.getSide(), new LinkedList<>());
        orders.get(order.getPrice()).get(order.getSide()).add(order);
    }

    // Method to delete an order from the orderbook by its id
    public void deleteOrder(int orderId) {
        for (Map<String, Queue<Order>> sideMap : orders.values()) {
            for (Queue<Order> orderQueue : sideMap.values()) {
                orderQueue.removeIf(order -> order.getId() == orderId);
            }
        }
    }

    // Method to modify an order's quantity by its id
    public void modifyOrder(int orderId, int newQuantity) {
        for (Map<String, Queue<Order>> sideMap : orders.values()) {
            for (Queue<Order> orderQueue : sideMap.values()) {
                for (Order order : orderQueue) {
                    if (order.getId() == orderId) {
                        order.modifyQuantity(newQuantity);
                        // After modification, losing priority (FIFO)
                        orderQueue.remove(order);
                        orderQueue.add(order);
                        return;
                    }
                }
            }
        }
    }

    // Method to access all orders at a given price level and side
    public Queue<Order> getOrdersAtPriceLevel(double price, String side) {
        if (orders.containsKey(price) && orders.get(price).containsKey(side)) {
            return orders.get(price).get(side);
        }
        return new LinkedList<>();
    }
}