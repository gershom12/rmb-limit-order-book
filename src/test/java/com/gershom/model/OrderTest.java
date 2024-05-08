package test.java.com.gershom.model;

import main.java.com.gershom.model.Order;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void testConstructor() {
        Order order = new Order(10.5, 100, "Buy");
        assertNotNull(order);
        assertEquals(10.5, order.getPrice(), 0.01);
        assertEquals(100, order.getQuantity());
        assertEquals("Buy", order.getSide());
    }

    @Test
    public void testGetId() {
        Order order = new Order(10.5, 100, "Buy");
        assertEquals(1, order.getId()); // Assuming nextId starts from 1
    }

    @Test
    public void testModifyQuantity() {
        Order order = new Order(10.5, 100, "Buy");
        order.modifyQuantity(50);
        assertEquals(50, order.getQuantity());
    }

    @Test
    public void testGetPrice() {
        Order order = new Order(10.5, 100, "Buy");
        assertEquals(10.5, order.getPrice(), 0.01);
    }

    @Test
    public void testGetQuantity() {
        Order order = new Order(10.5, 100, "Buy");
        assertEquals(100, order.getQuantity());
    }

    @Test
    public void testGetSide() {
        Order order = new Order(10.5, 100, "Buy");
        assertEquals("Buy", order.getSide());
    }

    @Test
    public void testModifyQuantityNegativeValue() {
        Order order = new Order(10.5, 100, "Buy");
        order.modifyQuantity(-50);
        assertEquals(100, order.getQuantity()); // Quantity should not change for negative values
    }

    @Test
    public void testModifyQuantityZeroValue() {
        Order order = new Order(10.5, 100, "Buy");
        order.modifyQuantity(0);
        assertEquals(0, order.getQuantity()); // Quantity should be allowed to be set to 0
    }

    @Test
    public void testModifyQuantityLargeValue() {
        Order order = new Order(10.5, 100, "Buy");
        order.modifyQuantity(1000);
        assertEquals(1000, order.getQuantity()); // Quantity can be set to a large value
    }

    @Test
    public void testModifyQuantityAfterConstructor() {
        Order order = new Order(10.5, 100, "Buy");
        order = new Order(15.0, 200, "Sell");
        order.modifyQuantity(150);
        assertEquals(150, order.getQuantity()); // Modify quantity after changing order details
    }
}
