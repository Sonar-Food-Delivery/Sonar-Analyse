package com.app.livraison.controller;

import com.app.livraison.entities.Order;
import com.app.livraison.service.OrderService;
import com.app.livraison.entities.Comment;
import com.app.livraison.entities.Delivery;
import com.app.livraison.entities.DeliveryPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.saveOrder(order);

        orderService.assignOrderToDeliveryPerson(createdOrder);

        return createdOrder;
    }

    @PostMapping("/{orderId}/comments")
    public Comment addComment(@PathVariable Long orderId, @RequestBody Comment comment) {
        return orderService.addCommentToOrder(orderId, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
