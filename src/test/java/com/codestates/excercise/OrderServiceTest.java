package com.codestates.excercise;

import com.codestates.excercise.item.Item;
import com.codestates.excercise.item.ItemRepository;
import com.codestates.excercise.order.Order;
import com.codestates.excercise.order.OrderRepository;
import com.codestates.excercise.order.OrderService;
import com.codestates.excercise.orderitem.OrderItem;
import com.codestates.excercise.orderitem.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    void clear() {
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        orderItemRepository.deleteAll();
    }

    @DisplayName("주문 추가")
    @Test
    @Transactional
    void addOrderItem() {
        // given
        Item item = itemRepository.save(new Item("item1", 1000, 99));
        Order order = orderRepository.save(new Order());

        // when
        Long orderId = orderService.addOrderItem(order.getId(), item.getId(), 10);

        // then
        Order findOrder = orderRepository.findById(orderId).orElseThrow();
        assertThat(findOrder).isNotNull();
    }

    @Test
    void jap_n_plus_one() {
        Order order = orderRepository.save(new Order());

        for (int i = 0; i < 10; i++) {
            Item item = itemRepository.save(new Item("item" + i, 1000, 99));
            order.addOrderItem(new OrderItem(order, item, i + 10));
        }
        orderRepository.save(order);

        System.out.println(orderService.findAllPrice(order.getId()));
    }
}
