package test.java.com.gershom.orderbook;

import main.java.com.gershom.model.Order;
import main.java.com.gershom.orderbook.Orderbook;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderbookTest {

    @Test
    public void testAddOrder() {
        Orderbook orderbook = new Orderbook();
        Order order = new Order(10.5, 100, "Buy");
        orderbook.addOrder(order);
        assertEquals(order, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek());
    }

    @Test
    public void testDeleteOrder() {
        Orderbook orderbook = new Orderbook();
        Order order = new Order(10.5, 100, "Buy");
        orderbook.addOrder(order);
        orderbook.deleteOrder(order.getId());
        assertTrue(orderbook.getOrdersAtPriceLevel(10.5, "Buy").isEmpty());
    }

    @Test
    public void testModifyOrder() {
        Orderbook orderbook = new Orderbook();
        Order order = new Order(10.5, 100, "Buy");
        orderbook.addOrder(order);
        orderbook.modifyOrder(order.getId(), 50);
        assertEquals(50, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek().getQuantity());
    }

    @Test
    public void testGetOrdersAtPriceLevelEmpty() {
        Orderbook orderbook = new Orderbook();
        assertTrue(orderbook.getOrdersAtPriceLevel(10.5, "Buy").isEmpty());
    }

    @Test
    public void testGetOrdersAtPriceLevelNotEmpty() {
        Orderbook orderbook = new Orderbook();
        Order order1 = new Order(10.5, 100, "Buy");
        Order order2 = new Order(10.5, 200, "Buy");
        orderbook.addOrder(order1);
        orderbook.addOrder(order2);
        assertEquals(order1, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek());
    }

    @Test
    public void testModifyOrderLossPriority() {
        Orderbook orderbook = new Orderbook();
        Order order1 = new Order(10.5, 100, "Buy");
        Order order2 = new Order(10.5, 200, "Buy");
        orderbook.addOrder(order1);
        orderbook.addOrder(order2);
        orderbook.modifyOrder(order1.getId(), 50);
        assertEquals(order2, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek());
    }

    @Test
    public void testModifyOrderNonExistentId() {
        Orderbook orderbook = new Orderbook();
        Order order = new Order(10.5, 100, "Buy");
        orderbook.addOrder(order);
        orderbook.modifyOrder(order.getId() + 1, 50);
        assertEquals(100, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek().getQuantity());
    }

    @Test
    public void testDeleteOrderNonExistentId() {
        Orderbook orderbook = new Orderbook();
        Order order = new Order(10.5, 100, "Buy");
        orderbook.addOrder(order);
        orderbook.deleteOrder(order.getId() + 1);
        assertEquals(1, orderbook.getOrdersAtPriceLevel(10.5, "Buy").size());
    }

    @Test
    public void testAddOrderMultipleSides() {
        Orderbook orderbook = new Orderbook();
        Order buyOrder = new Order(10.5, 100, "Buy");
        Order sellOrder = new Order(10.5, 200, "Sell");
        orderbook.addOrder(buyOrder);
        orderbook.addOrder(sellOrder);
        assertEquals(buyOrder, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek());
        assertEquals(sellOrder, orderbook.getOrdersAtPriceLevel(10.5, "Sell").peek());
    }

    @Test
    public void testAddOrderMultiplePrices() {
        Orderbook orderbook = new Orderbook();
        Order order1 = new Order(10.5, 100, "Buy");
        Order order2 = new Order(11.0, 200, "Buy");
        orderbook.addOrder(order1);
        orderbook.addOrder(order2);
        assertEquals(order1, orderbook.getOrdersAtPriceLevel(10.5, "Buy").peek());
        assertEquals(order2, orderbook.getOrdersAtPriceLevel(11.0, "Buy").peek());
    }
}
