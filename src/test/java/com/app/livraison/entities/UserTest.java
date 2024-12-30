package com.app.livraison.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("TestUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
    }

    @Test
    void testUserGettersAndSetters() {
        assertEquals(1L, user.getId());
        assertEquals("TestUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testUserWithOrdersAndComments() {
        Order order = new Order();
        order.setId(101L);
        order.setUser(user);

        Comment comment = new Comment();
        comment.setId(201L);
        comment.setUser(user);

        user.setOrders(List.of(order));
        user.setComments(List.of(comment));

        assertEquals(1, user.getOrders().size());
        assertEquals(1, user.getComments().size());
    }

    @Test
    void testUserEquality() {
        User anotherUser = new User();
        anotherUser.setId(1L);
        anotherUser.setEmail("test@example.com");
        anotherUser.setUsername("TestUser");
        anotherUser.setPassword("password123");  // Assurez-vous de définir tous les champs

        assertEquals(user, anotherUser);
        assertEquals(user.hashCode(), anotherUser.hashCode());
    }

}
