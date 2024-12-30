package com.app.livraison.services;

import com.app.livraison.entities.Order;
import com.app.livraison.entities.Comment;
import com.app.livraison.entities.Delivery;
import com.app.livraison.entities.DeliveryPerson;
import com.app.livraison.repositorie.OrderRepository;
import com.app.livraison.repositorie.CommentRepository;
import com.app.livraison.repositorie.DeliveryRepository;
import com.app.livraison.repositorie.DeliveryPersonRepository;
import com.app.livraison.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DeliveryPersonRepository deliveryPersonRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private Comment comment;
    private DeliveryPerson deliveryPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setStatus("Pending");

        comment = new Comment();
        comment.setId(1L);
        comment.setContent("Great service");

        deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(1L);
        deliveryPerson.setStatus("AVAILABLE");
    }

    @Nested
    class GetOrdersTests {

        @Test
        void testGetAllOrders() {
            when(orderRepository.findAll()).thenReturn(List.of(order));

            List<Order> orders = orderService.getAllOrders();

            assertNotNull(orders);
            assertFalse(orders.isEmpty());
            assertEquals(1, orders.size());
        }

        @Test
        void testGetOrderById_Success() {
            when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

            Order foundOrder = orderService.getOrderById(1L);

            assertNotNull(foundOrder);
            assertEquals(1L, foundOrder.getId());
        }

        @Test
        void testGetOrderById_NotFound() {
            when(orderRepository.findById(1L)).thenReturn(Optional.empty());

            Order foundOrder = orderService.getOrderById(1L);

            assertNull(foundOrder);
        }
    }

    @Nested
    class SaveOrderTests {

        @Test
        void testSaveOrder_Success() {
            when(orderRepository.save(order)).thenReturn(order);

            Order savedOrder = orderService.saveOrder(order);

            assertNotNull(savedOrder);
            assertEquals(1L, savedOrder.getId());
        }
    }

    @Nested
    class DeleteOrderTests {

        @Test
        void testDeleteOrder_Success() {
            doNothing().when(orderRepository).deleteById(1L);

            assertDoesNotThrow(() -> orderService.deleteOrder(1L));

            verify(orderRepository, times(1)).deleteById(1L);
        }
    }

    @Nested
    class CommentOrderTests {

        @Test
        void testAddCommentToOrder_Success() {
            when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
            when(commentRepository.save(comment)).thenReturn(comment);

            Comment addedComment = orderService.addCommentToOrder(1L, comment);

            assertNotNull(addedComment);
            assertEquals("Great service", addedComment.getContent());
        }

        @Test
        void testAddCommentToOrder_OrderNotFound() {
            when(orderRepository.findById(1L)).thenReturn(Optional.empty());

            Comment addedComment = orderService.addCommentToOrder(1L, comment);

            assertNull(addedComment);
        }
    }

    @Nested
    class AssignOrderTests {

        @Test
        void testAssignOrderToDeliveryPerson_Success() {
            when(deliveryPersonRepository.findFirstByStatus("AVAILABLE"))
                    .thenReturn(deliveryPerson);
            when(deliveryRepository.save(any(Delivery.class))).thenReturn(new Delivery());

            assertDoesNotThrow(() -> orderService.assignOrderToDeliveryPerson(order));

            assertEquals("UNAVAILABLE", deliveryPerson.getStatus());
            verify(deliveryPersonRepository, times(1)).save(deliveryPerson);
        }

        @Test
        void testAssignOrderToDeliveryPerson_NoAvailablePerson() {
            when(deliveryPersonRepository.findFirstByStatus("AVAILABLE"))
                    .thenReturn(null);

            assertDoesNotThrow(() -> orderService.assignOrderToDeliveryPerson(order));

            verify(deliveryPersonRepository, never()).save(any(DeliveryPerson.class));
            verify(deliveryRepository, never()).save(any(Delivery.class));
        }
    }
}
