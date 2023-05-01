package com.codestates.excercise.order;

import com.codestates.excercise.orderitem.OrderItem;
import com.codestates.excercise.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public  void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public static Order createOrder(Item item, int count) {
        var order = new Order();
        order.orderItems.add(new OrderItem(order, item, count));
        return order;
    }

}
