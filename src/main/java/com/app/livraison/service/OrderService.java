package com.app.livraison.service;

import com.app.livraison.entities.Order;
import com.app.livraison.entities.Comment;
import com.app.livraison.entities.Delivery;
import com.app.livraison.entities.DeliveryPerson;
import com.app.livraison.repositorie.OrderRepository;
import com.app.livraison.repositorie.CommentRepository;
import com.app.livraison.repositorie.DeliveryRepository;
import com.app.livraison.repositorie.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CommentRepository commentRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Comment addCommentToOrder(Long orderId, Comment comment) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            comment.setOrder(order);
            return commentRepository.save(comment);
        }
        return null;
    }

    public void saveDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public DeliveryPerson findAvailableDeliveryPerson() {
        return deliveryPersonRepository.findFirstByStatus("AVAILABLE");
    }

    public void assignOrderToDeliveryPerson(Order order) {
        DeliveryPerson deliveryPerson = findAvailableDeliveryPerson();

        if (deliveryPerson != null) {
            deliveryPerson.setStatus("UNAVAILABLE");
            deliveryPersonRepository.save(deliveryPerson);
            Delivery delivery = new Delivery();
            delivery.setOrder(order);
            delivery.setDeliveryPerson(deliveryPerson);
            delivery.setTrackingId(generateTrackingId());
            delivery.setCurrentLocation("En attente");
            delivery.setEstimatedDeliveryTime("En cours");

            saveDelivery(delivery);
        }
    }

    private String generateTrackingId() {
        return "TRK" + System.currentTimeMillis();
    }
}
