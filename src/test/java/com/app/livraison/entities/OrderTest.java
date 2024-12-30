package com.app.livraison.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private User user;
    private Meal meal;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("JohnDoe");

        meal = new Meal();
        meal.setId(101L);
        meal.setName("Pizza Napoli");

        order = new Order();
        order.setId(1L);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(29.99);
        order.setUser(user);
        order.setMeals(List.of(meal));
    }

    @Test
    void testOrderGettersAndSetters() {
        assertEquals(1L, order.getId());
        assertEquals("Pending", order.getStatus());
        assertEquals(29.99, order.getTotalAmount());
        assertEquals(user, order.getUser());
        assertNotNull(order.getMeals());
        assertEquals(1, order.getMeals().size());
        assertEquals("Pizza Napoli", order.getMeals().get(0).getName());
    }

    @Test
    void testOrderEquality() {
        Order anotherOrder = new Order();
        anotherOrder.setId(1L);
        anotherOrder.setStatus("Pending");
        anotherOrder.setTotalAmount(29.99);
        anotherOrder.setUser(user);
        anotherOrder.setOrderDate(order.getOrderDate());  // Initialiser la même date

        order.setMeals(null);  // Éviter StackOverflow
        anotherOrder.setMeals(null);

        assertEquals(order, anotherOrder);  // Comparaison d'égalité
        assertEquals(order.hashCode(), anotherOrder.hashCode());  // Comparaison de hashcode
    }


    @Test
    void testOrderHashCode() {
        order.setMeals(null);
        assertDoesNotThrow(() -> order.hashCode());
    }

    @Test
    void testOrderToString() {
        order.setMeals(null);
        String result = order.toString();
        assertTrue(result.contains("Pending"));
        assertTrue(result.contains("29.99"));
    }
}
