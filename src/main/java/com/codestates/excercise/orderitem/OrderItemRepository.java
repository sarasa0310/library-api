package com.codestates.excercise.orderitem;

import com.codestates.excercise.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByOrder(Order order);

    OrderItem findByOrderId(Long orderId);
}
