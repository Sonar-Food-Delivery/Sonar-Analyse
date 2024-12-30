package com.app.livraison.Controllers;

import com.app.livraison.controller.OrderController;
import com.app.livraison.entities.Order;
import com.app.livraison.entities.Comment;
import com.app.livraison.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private Order order;
    private Comment comment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setStatus("Pending");

        comment = new Comment();
        comment.setId(1L);
        comment.setContent("Great service");
    }

    @Test
    void testGetAllOrders() {
        when(orderService.getAllOrders()).thenReturn(List.of(order));

        List<Order> orders = orderController.getAllOrders();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
    }

    @Test
    void testGetOrderById_Success() {
        when(orderService.getOrderById(1L)).thenReturn(order);

        Order foundOrder = orderController.getOrderById(1L);

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderService.getOrderById(1L)).thenReturn(null);

        Order foundOrder = orderController.getOrderById(1L);

        assertNull(foundOrder);
    }

    @Test
    void testCreateOrder() {
        when(orderService.saveOrder(order)).thenReturn(order);

        Order createdOrder = orderController.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderService).deleteOrder(1L);

        assertDoesNotThrow(() -> orderController.deleteOrder(1L));

        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    void testAddComment() {
        when(orderService.addCommentToOrder(1L, comment)).thenReturn(comment);

        Comment addedComment = orderController.addComment(1L, comment);

        assertNotNull(addedComment);
        assertEquals("Great service", addedComment.getContent());
    }
}
