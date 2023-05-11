package com.codestates.excercise;

import com.codestates.excercise.item.Item;
import com.codestates.excercise.item.ItemRepository;
import com.codestates.excercise.order.Order;
import com.codestates.excercise.order.OrderRepository;
import com.codestates.excercise.orderitem.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderItemRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    private Order order;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        orderItemRepository.deleteAll();
        Item item = itemRepository.save(new Item("item", 10000, 100));
        order = orderRepository.save(Order.createOrder(item, 10));
    }

    @Test
    @Disabled
    void findById_getReferenceById() {
        Order findByIdOrder = orderRepository.findById(order.getId()).orElseThrow();
        Order referenceByIdOrder = orderRepository.getReferenceById(order.getId());
        System.out.println("findByIdOrder.getId() = " + findByIdOrder.getId());
        System.out.println("referenceByIdOrder.getId() = " + referenceByIdOrder.getId());
        System.out.println("findByIdOrder.getOrderDate() = " + findByIdOrder.getOrderDate());
        System.out.println("referenceByIdOrder.getOrderDate() = " + referenceByIdOrder.getOrderDate());
    }

    @Test
    void findByEntityId_findByEntity() {
        orderItemRepository.findByOrder(order);
        orderItemRepository.findByOrderId(order.getId());
    }
}
